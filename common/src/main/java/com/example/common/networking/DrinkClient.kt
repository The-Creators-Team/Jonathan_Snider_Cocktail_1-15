package com.example.common.networking

import com.example.common.api.APIDetails
import com.example.common.model.Drink
import com.example.common.model.DrinkList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import com.example.common.util.NetworkError
import com.example.common.util.Result

class DrinkClient(
    private val httpClient: HttpClient
) {
    suspend fun getDrinks(): Result<DrinkList, NetworkError>  {
        val response = try {
            httpClient.get(
                //urlString = APIDetails.BASE_URL + APIDetails.ENDPOINT_ROOMS
                urlString="https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a"
            )
        }
        //the client cannot find the URL provided or there is not internet connection
        catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        }
        //poorly formatted request
        catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        //if parameters needed to be sent with the request, they could be added here:
        /*{
            parameter("example", "example")
            header("Content-Type", "application/json")

            //obviously get requests can't have bodies but if this was a post request this is
            //how you would add it:

            contentType(ContentType.Application.Json)
            setBody(
            in here you would pass an instance of an object from a class marked with
            serializable to convert it to json format
            )
        }*/
        return when(response.status.value){
            in 200..299 ->{
                val drinkList=response.body<DrinkList>()
                Result.Success(drinkList)
            }
            401->{
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            408->{
                Result.Error(NetworkError.REQUEST_TIMEOUT)
            }
            409->{
                Result.Error(NetworkError.CONFLICT)
            }
            413->{
                Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            }
            in 500..599->{
                Result.Error(NetworkError.SERVER_ERROR)
            }
            else->{
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    suspend fun searchDrinksName(searchString: String): Result<DrinkList, NetworkError>  {
        val response = try {
            httpClient.get(
                urlString = APIDetails.SEARCH_BY_NAME_BASE+searchString
                //urlString="https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a"
            )
        }
        //the client cannot find the URL provided or there is not internet connection
        catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        }
        //poorly formatted request
        catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        //if parameters needed to be sent with the request, they could be added here:
        /*{
            parameter("example", "example")
            header("Content-Type", "application/json")

            //obviously get requests can't have bodies but if this was a post request this is
            //how you would add it:

            contentType(ContentType.Application.Json)
            setBody(
            in here you would pass an instance of an object from a class marked with
            serializable to convert it to json format
            )
        }*/
        return when(response.status.value){
            in 200..299 ->{
                val drinkList=response.body<DrinkList>()
                Result.Success(drinkList)
            }
            401->{
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            408->{
                Result.Error(NetworkError.REQUEST_TIMEOUT)
            }
            409->{
                Result.Error(NetworkError.CONFLICT)
            }
            413->{
                Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            }
            in 500..599->{
                Result.Error(NetworkError.SERVER_ERROR)
            }
            else->{
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    suspend fun searchDrinksLetter(searchLetter: String): Result<DrinkList, NetworkError>  {
        val response = try {
            httpClient.get(
                urlString = APIDetails.SEARCH_BY_LETTER_BASE+searchLetter
                //urlString="https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a"
            )
        }
        //the client cannot find the URL provided or there is not internet connection
        catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        }
        //poorly formatted request
        catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        //if parameters needed to be sent with the request, they could be added here:
        /*{
            parameter("example", "example")
            header("Content-Type", "application/json")

            //obviously get requests can't have bodies but if this was a post request this is
            //how you would add it:

            contentType(ContentType.Application.Json)
            setBody(
            in here you would pass an instance of an object from a class marked with
            serializable to convert it to json format
            )
        }*/
        return when(response.status.value){
            in 200..299 ->{
                val drinkList=response.body<DrinkList>()
                Result.Success(drinkList)
            }
            401->{
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            408->{
                Result.Error(NetworkError.REQUEST_TIMEOUT)
            }
            409->{
                Result.Error(NetworkError.CONFLICT)
            }
            413->{
                Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            }
            in 500..599->{
                Result.Error(NetworkError.SERVER_ERROR)
            }
            else->{
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}