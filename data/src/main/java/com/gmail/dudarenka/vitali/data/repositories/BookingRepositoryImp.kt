package by.a750mm.excursionsapp750mm.data.repositories

import com.gmail.dudarenka.vitali.data.net.RestService
import by.a750mm.excursionsapp750mm.domain.entity.Booking
import by.a750mm.excursionsapp750mm.domain.repositories.BookingRepository
import io.reactivex.Observable

class BookingRepositoryImp(private val apiService: RestService) : BookingRepository {
    override fun add(booking: Booking): Observable<Booking> {
        return apiService.addBooking(booking.transformToData()).map {
            it.transformToDomain()
        }
    }
}