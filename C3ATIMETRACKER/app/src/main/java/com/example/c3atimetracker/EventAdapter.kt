import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c3atimetracker.Event
import com.example.c3atimetracker.R


class EventAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    private val eventList: MutableList<Event> = mutableListOf()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val editTextDate: TextView = itemView.findViewById(R.id.editTextDate)
        private val textViewTime: TextView = itemView.findViewById(R.id.textViewTime)
        private val descrText: TextView = itemView.findViewById(R.id.descrText)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val event = eventList[position]
                    listener.onItemClick(event)
                }
            }
        }

        fun bind(event: Event) {
            editTextDate.text = event.Date
            textViewTime.text = "${event.startTime} - ${event.endTime}"
            descrText.text = event.description
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun setEvents(events: List<Event>) {
        eventList.clear()
        eventList.addAll(events)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(event: Event)
    }
}