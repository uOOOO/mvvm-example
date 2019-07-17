package com.uoooo.simple.example.interactor

import com.uoooo.simple.example.domain.interactor.GetMovieUseCase
import com.uoooo.simple.example.domain.interactor.GetMovieUseCaseImpl
import com.uoooo.simple.example.domain.repository.MovieRepository
import io.mockk.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetMovieUseCaseTest {
    private val movieRepository: MovieRepository = mockk()
    private var getMovieUseCase: GetMovieUseCase = GetMovieUseCaseImpl(movieRepository)

    @Before
    fun before() {
        clearMocks(movieRepository)
    }

    @Test
    fun getPopularMovie() {
        every { movieRepository.getPopular(any()) } returns Single.just(mockk(relaxed = true))
        getMovieUseCase.getPopularMovie(1)
            .test()
            .await()
            .assertComplete()
        verify { movieRepository.getPopular(any()) }
    }
}