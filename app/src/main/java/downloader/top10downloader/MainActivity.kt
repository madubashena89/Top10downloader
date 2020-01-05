package downloader.top10downloader

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class FeedEntry{
    var name : String = ""
    var artist : String = ""
    var releseDate : String = ""
    var summery = ""
    var imageURL = ""

    override fun toString(): String {
        return  """" name= $name
                     artist= $artist
                     releseDate= $releseDate
                     summery= $summery
                     imageURL= $imageURL""".trimIndent()
    }


}

class MainActivity : AppCompatActivity() {

    private val TAG = " Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called")
        val downloadData = DownloadData()
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d(TAG, "onCreate : done")

    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>(){

            private val TAG = "DownloadData"

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
//                Log.d(TAG, "onPostExecute : parameter is $result")
                val parseApplications = ParseApplications()
                parseApplications.parse(result)
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground: starts with ${url[0]}")
                return "doInbackground completed"

                val rssFeed = downloadXML(url[0])
                if(rssFeed.isEmpty()){
                    Log.e(TAG, "Error in downloading")
                }
                return rssFeed
            }

            private fun downloadXML(urlPath: String?) : String {
                return URL(urlPath).readText()
//                val xmlResult = StringBuilder()
//
//                try {
//                    val url = URL(urlPath)
//                    val connection : HttpURLConnection = url.openConnection() as HttpURLConnection
//                    val response = connection.responseCode
//                    Log.d(TAG, "downloadXML : The response code was $response")
//
//                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
//
//                    connection.inputStream.buffered().reader().use { xmlResult.append(it.readText()) }
//
//                    Log.d(TAG, "Received ${xmlResult.length} bytes")
//                    return xmlResult.toString()
//
//                }
//                 catch (e : Exception){
//                     val errorMesseage = when(e){
//                         is MalformedURLException -> "downloadXML : IO Exception reading data ${e.message}"
//                         is IOException -> "downloadXML : IO Exception reading data ${e.message}"
//                         is SecurityException -> { e.printStackTrace()
//                             "downloadXML : Security exception : Needs Permissions? ${e.message}"
//                         }
//                         else -> "Unknown error: ${e.message}"
//                     }
//                 }

//                return "" // If it gets to here, there's been a problem. Return an empty string

            }


        }

    }


}
