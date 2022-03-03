package com.azatberdimyradov.parkingspotfinder.di

import android.app.Application
import androidx.room.Room
import com.azatberdimyradov.parkingspotfinder.data.ParkingSpotDatabase
import com.azatberdimyradov.parkingspotfinder.data.ParkingSpotRepositoryImpl
import com.azatberdimyradov.parkingspotfinder.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): ParkingSpotDatabase {
        return Room.databaseBuilder(
            app,
            ParkingSpotDatabase::class.java,
            "parking_spot_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepository(db: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(db.dao)
    }
}