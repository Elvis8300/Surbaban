package com.example.surbaban.models

class Product {
    var name:String = ""
    var quantity:String = ""
    var price:String = ""
    var imageurl:String = ""
    var id:String = ""

    constructor(name: String, quantity: String, price: String, image: String, id: String) {
        this.name = name
        this.quantity = quantity
        this.price = price
        this.imageurl = image
        this.id = id
    }
    constructor()
}