package by.a750mm.excursionsapp750mm.data.repositories

import by.a750mm.excursionsapp750mm.data.db.dao.ExcursionDao
import by.a750mm.excursionsapp750mm.data.db.dao.PortfolioDao
import by.a750mm.excursionsapp750mm.data.db.entity.transformToDB
import by.a750mm.excursionsapp750mm.data.db.entity.transformToDomain
import com.gmail.dudarenka.vitali.data.net.RestService
import by.a750mm.excursionsapp750mm.domain.entity.Portfolio
import by.a750mm.excursionsapp750mm.domain.repositories.PortfolioRepository
import io.reactivex.Observable

class PortfolioRepositoryImp(private val apiService: RestService, private val portfolioDao: PortfolioDao) : PortfolioRepository {


    companion object {
        const val TIME_BUFFER = 600000
    }

    private var lastTimeUpdate: Long = 0
    override fun get(): Observable<List<Portfolio>> {
        return portfolioDao.getAll()
                .take(1)
                .toObservable()
                .flatMap { portfolioDBList ->
                    if (portfolioDBList.isEmpty() || System.currentTimeMillis() - lastTimeUpdate > PortfolioRepositoryImp.TIME_BUFFER) {
                        apiService.getPortfolios()
                                .doOnNext {
                                    lastTimeUpdate = System.currentTimeMillis()
                                    val list = it.map { it.transformToDB() }
                                    portfolioDao.deleteAll()
                                    portfolioDao.insert(list)
                                }
                                .map { list ->
                                    list.map { portfolio -> portfolio.transformToDomain() }
                                }
                                .onErrorReturn {
                                    if (portfolioDBList.isEmpty()) {
                                        throw it
                                    } else {
                                        portfolioDBList
                                                .map { it ->
                                                    it.transformToDomain()
                                                }
                                    }
                                }
                    } else {
                        Observable.just(portfolioDBList).map { list ->
                            list.map { portfolio -> portfolio.transformToDomain() }
                        }
                    }
                }

    }


    override fun getById(id: String): Observable<Portfolio> {
        return apiService.getPortfolioById(id).map {
            it.transformToDomain()
        }
    }

}