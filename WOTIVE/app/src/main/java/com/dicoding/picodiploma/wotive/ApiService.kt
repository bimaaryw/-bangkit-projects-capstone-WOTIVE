import com.dicoding.picodiploma.wotive.WorkshopResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("detail/{merek}")
    fun getWorkshop(
        @Path("merek") id: String
    ): Call<WorkshopResponse>
}