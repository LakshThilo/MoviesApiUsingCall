package com.example.testmovieapiusingcall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testmovieapiusingcall.model.MovieResponse
import com.example.testmovieapiusingcall.model.remote.HttpRequests
import com.example.testmovieapiusingcall.view.MovieFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        HttpRequests.api.getMovies().enqueue(
            object : Callback <MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {

                    Log.d(TAG, "onResponse: ${response.body()}")
                   // Toast.makeText(this@MainActivity,"${response.body()}", Toast.LENGTH_SHORT).show()
                    createFragment(response.body())


                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                }

            }
        )
    }

    private fun createFragment(body: MovieResponse?) {

        body?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MovieFragment.newInstance(it))
                .commit()
        }
    }
}

/*
*   1. Debug/ Analyze the Endpoint              - URL
*   2. Create data class                        - MovieResponse
*   3. Add dependencies                         - gradle
*   4. Create End Points  (Retrofit Interface)  - MovieApi
*   5. Configure the Retrofit Client            - HttpRequest
*   6. Consume the request (Execute / Enqueue)  - MainActivity
*
* */