db.dropDatabase("food_dispatch")

db = db.getSiblingDB("food_dispatch")

db.createCollection("restaurants")
db.createCollection("delivery_services")

db.createCollection("drivers")

db.createCollection("consumers")

db.createCollection("orders")

var consumer_id = ObjectId()

db.consumers.insertOne(
    {
        _id: consumer_id,
        first_name: "Pierre",
        last_name: "The Frenchie",
        orders: [],
        addresses: [
            "Isep Issy 306",
            "Isep NDC 28"],
        preferences: {
            likes: [],
            dislikes: [],
            allorgens: []
        },
        email: "pierre@france.fr",
        credentials: {username: "Pierre", password: "1234"}
    })

var rating_id1 = ObjectId()
var rating_id2 = ObjectId()
var coke_id = ObjectId()
var americano_id = ObjectId()

var delivery_service_id = ObjectId()
var driver_id = ObjectId()

db.food_items.insertOne(
    {
        _id: coke_id,
        name: "Coca-Cola",
        price: 0.5,
        tags: [],
        tools_req: [],
        photo_url: "",
        ratings: []
    }
)

db.food_items.insertOne(
    {
        _id: americano_id,
        name: "The americano",
        price: 2,
        tags: [],
        tools_req: [],
        photo_url: "",
        ratings: []
    }
)


db.restaurants.insertOne(
    {
        name: "Panini Land",
        food_items: [coke_id, americano_id],
        combos: [
            {name: "Formula A", price: 6.2, tags: [], food_items: [coke_id, americano_id]}
        ],
        ratings: [],
        address: "A cote de notra-dame",
        tags: [],
        delivery_services: [delivery_service_id],
        orders: [],
        email: "panini.land@france.fr",
        credentials: {username: "panini", password: "1234"},
    })

db.restaurants.updateOne({name: "Panini Land"},
    {$push: {ratings: {_id: rating_id1, feedback: "Great Value", value: 5, consumer_id: consumer_id}}})

db.restaurants.find({name: "Panini Land"}, {ratings: {consumer_id: 1}})
db.restaurants.find({name: "Panini Land"})


db.delivery_services.insertOne({
    name: "Uber",
    drivers: [driver_id],
    fee: 1.2
})

db.drivers.insertOne({
    _id: driver_id,
    first_name: "Patrick",
    last_name: "Star",
    ratings: [rating_id2],
    email: "under.rock@france.fr",
    credentials: {username: "patric", password: "1234"},
    tools: [],
    current_pos: "",
    orders: []
})

var order_id = ObjectId()

db.orders.insertOne({
    _id: order_id,
    items: [],
    total: 6.2,
    status: "complete",
    driver: driver_id,
    delivery_address: "Isep NDC 28",
    payment: {method: "card", type: "visa"}
})


db.restaurants.find({name: "Panini Land"}, {food_items: 1}).forEach(i => {
    db.orders.updateOne({_id: order_id}, {$push: {items: i}})
})


db.consumers.updateOne({_id: consumer_id}, {$push: {orders: order_id}})
db.restaurants.updateOne({name: "Panini Land"}, {$push: {orders: order_id}})
db.drivers.updateOne({_id: driver_id}, {$push: {orders: order_id}})

db.tags.insertOne({
    name: "vegeterian"
})

db.tools.insertOne({
    name: "bike",
    type: "vehicle"
})

db.ratings.insertOne({
    _id: rating_id2,
    feedback: "All the food arrived soaked wet",
    value: 1,
    consumer_id: consumer_id
})
