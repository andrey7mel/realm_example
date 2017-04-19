package com.andrey7mel.realm_examlpe.presenter

import com.andrey7mel.realm_examlpe.model.Model

open class BasePresenter(var model: Model) {
    fun addObject() = model.addObject()

    fun deleteObject(id: Long) = model.deleteObject(id)

}