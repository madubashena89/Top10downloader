package downloader.top10downloader

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class FeedAdapter (context : Context, private val resource : Int, private val applications : List<FeedEntry>): ArrayAdapter<FeedEntry>(context, resource){
    private val TAG = "FeedAdapter"
    private val inflater = LayoutInflater.from(context)


    override fun getCount(): Int {
        Log.d(TAG, "getCount() called")
        return applications.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d(TAG, "getView() called")

       val view = inflater.inflate(resource, parent, false)

        val tvName : TextView = view.findViewById(R.id.tvName)
        val tvArtist: TextView = view.findViewById(R.id.tvArtist)
        val tvSummery : TextView = view.findViewById(R.id.tvSummery)

        val currentApp = applications[position]

        tvName.text = currentApp.name
        tvArtist.text = currentApp.artist
        tvSummery.text = currentApp.summery

        return  view
    }


}