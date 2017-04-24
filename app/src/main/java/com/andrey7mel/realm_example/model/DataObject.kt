package com.andrey7mel.realm_example.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DataObject : RealmObject {
    @PrimaryKey
    open var id: Long = 0

    open var name: String = "DataObject"


    constructor() : this(System.currentTimeMillis())

    constructor(id: Long) : super() {
        this.id = id
    }

}