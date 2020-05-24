package com.example.core

import com.example.core.data.CountryCategory
import com.example.core.usecase.GetAllDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetAllDataUseCaseTest {

    @MockK
    lateinit var dataRepository: DataRepository
    lateinit var getAllDataUseUseCase: GetAllDataUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.getAllDataUseUseCase = GetAllDataUseCase(dataRepository)
    }

    @Test
    fun `Get all data use case - network only -Should success with remote result`() = runBlocking {

        coEvery {
            dataRepository.getAllData(true)
        } returns UseCaseResult.Success(
            "", listOf(
                CountryCategory(
                    31,
                    31,
                    "ttt",
                    "",
                    arrayListOf()
                ),
                CountryCategory(
                    32,
                    32,
                    "ttt",
                    "",
                    arrayListOf()
                )
            )
        )

        var list = getAllDataUseUseCase.execute(Unit, true)
        var l: List<CountryCategory>? = null
        when (list) {
            is UseCaseResult.Success -> {
                l = list.data
            }
        }

        assertEquals(31, l!![0].countryCateId)
    }
}