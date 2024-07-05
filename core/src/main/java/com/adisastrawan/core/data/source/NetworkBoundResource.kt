package com.adisastrawan.core.data.source

import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {


    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }


    abstract suspend fun saveCallResult(data: RequestType)

    abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    abstract fun shouldFetch(dbSource: ResultType): Boolean

    protected abstract fun loadFromDB(): Flow<ResultType>

    fun asFlow(): Flow<Resource<ResultType>> = result

}
