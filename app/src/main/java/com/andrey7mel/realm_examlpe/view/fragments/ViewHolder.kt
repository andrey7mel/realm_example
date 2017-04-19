package com.andrey7mel.realm_examlpe.view.fragments

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import com.andrey7mel.realm_examlpe.model.DataObject
import com.andrey7mel.realm_examlpe.presenter.BasePresenter
import kotlinx.android.synthetic.main.item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ViewHolder(v: View, val presenter: BasePresenter) : RecyclerView.ViewHolder(v) {


    fun bindView(dataObject: DataObject) {
        itemView.title.text = formatter.format(Date(dataObject.id))
        itemView.title.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= itemView.title.right - itemView.title.totalPaddingRight) {
                    presenter.deleteObject(dataObject.id)
                    return@OnTouchListener true
                }
            }
            true
        })
    }

    companion object {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")
    }
}
