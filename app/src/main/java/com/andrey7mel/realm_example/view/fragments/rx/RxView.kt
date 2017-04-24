package com.andrey7mel.realm_example.view.fragments.rx

import com.andrey7mel.realm_example.model.DataObject

interface RxView {
    fun showData(list: List<DataObject>)
    fun showEmpty()
}

