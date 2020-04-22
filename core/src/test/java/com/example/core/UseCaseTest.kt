package com.example.core

import com.example.core.data.ResultResponse
import com.example.core.usecase.GetAllDataCase
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
class GetAllDataUseCaseTest{

    @MockK
    lateinit var dataRepository: DataRepository
    lateinit var getAllDataUseCase: GetAllDataCase

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        this.getAllDataUseCase = GetAllDataCase(dataRepository)
    }

    @Test
    fun `Get all data use case - network only -Should success with remote result`() = runBlocking{

        coEvery{
            dataRepository.getAllData()
        } returns UseCaseResult.Success(listOf(ResultResponse(
            32,
            "xx",
            "ttt",
            ArrayList()),
            ResultResponse(
                16,
                "xx",
                "ttt",
                ArrayList())))

        var list = getAllDataUseCase.execute()
        var l :List<ResultResponse>? = null
        when(list){
            is UseCaseResult.Success->{
                l = list.data
            }
        }

        assertEquals(31 , l!![0].parentCateId)
    }
}