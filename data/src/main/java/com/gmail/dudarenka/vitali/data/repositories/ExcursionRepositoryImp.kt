package by.a750mm.excursionsapp750mm.data.repositories

import by.a750mm.excursionsapp750mm.data.db.dao.ExcursionDao
import by.a750mm.excursionsapp750mm.data.db.entity.transformToDB
import by.a750mm.excursionsapp750mm.data.db.entity.transformToDomain
import com.gmail.dudarenka.vitali.data.net.RestService
import by.a750mm.excursionsapp750mm.domain.entity.Excursion
import by.a750mm.excursionsapp750mm.domain.repositories.ExcursionRepository
import io.reactivex.Observable

@Suppress("NAME_SHADOWING")
class ExcursionRepositoryImp(private val apiService: RestService, val excursionDao: ExcursionDao) : ExcursionRepository {


    companion object {
        const val TIME_BUFFER = 60000
    }

    private var lastTimeUpdate: Long = 0

    override fun get(): Observable<List<Excursion>> {
        return excursionDao.getAll()
                .take(1)
                .toObservable()
                .flatMap { excursionDBList ->
                    if (excursionDBList.isEmpty() || System.currentTimeMillis() - lastTimeUpdate > TIME_BUFFER) {
                        apiService.getExcursions()
                                .doOnNext {
                                    lastTimeUpdate = System.currentTimeMillis()
                                    val list = it.map { it.transformToDB() }
                                    excursionDao.deleteAll()
                                    excursionDao.insert(list)
                                }
                                .map { list ->
                                    list.map { excursion -> excursion.transformToDomain() }
                                }
                                .onErrorReturn {
                                    if (excursionDBList.isEmpty()) {
                                        throw it
                                    } else {
                                        excursionDBList
                                                .map { it ->
                                                    it.transformToDomain()
                                                }
                                    }
                                }
                    } else {
                        Observable.just(excursionDBList).map { list ->
                            list.map { excursion -> excursion.transformToDomain() }
                        }
                    }
                }


    }




    override fun getById(id: String): Observable<Excursion> {
        return apiService.getExcursionById(id).map {
            it.transformToDomain()
        }
    }

}