package com.example.c3atimetracker

object EventDataSource {
    private val eventList: MutableList<Event> = mutableListOf()

    fun getEvents(): List<Event> {
        return eventList
    }

    fun addEvent(event: Event) {
        eventList.add(event)
    }

    fun removeEvent(event: Event) {
        eventList.remove(event)
    }

    // Other methods to update or delete events

    // Example method to clear all events
    fun clearEvents() {
        eventList.clear()
    }
}