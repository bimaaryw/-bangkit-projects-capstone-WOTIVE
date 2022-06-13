import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.wotive.ApiConfig
import com.dicoding.picodiploma.wotive.Workshop
import com.dicoding.picodiploma.wotive.WorkshopResponse
import com.dicoding.picodiploma.wotive.databinding.ActivityMainBinding
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        private const val TAG = "MainActivity"
        private const val WORKSHOP_merek = "Bengkel555"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)
        findRestaurant()
    }

    private fun findRestaurant() {
        showLoading(true)
        val client = ApiConfig.getApiService().getWorkshop(WORKSHOP_merek)
        client.enqueue(object : Callback<WorkshopResponse> {
            override fun onResponse(
                call: Call<WorkshopResponse>,
                response: Response<WorkshopResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setWorkshopData(responseBody.workshop)

                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<WorkshopResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
    private fun setWorkshopDataData(workshop: Workshop) {
        binding.tvMerek.text = workshop.merek
        binding.tvAlamat.text = workshop.alamat
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}