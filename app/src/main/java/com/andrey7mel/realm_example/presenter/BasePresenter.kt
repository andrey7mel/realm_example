package com.andrey7mel.realm_example.presenter

import com.andrey7mel.realm_example.model.Model

open class BasePresenter(var model: Model) {
    fun addObject() = model.addObject()

    fun deleteObject(id: Long) = model.deleteObject(id)

}