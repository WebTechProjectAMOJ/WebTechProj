db.dropDatabase("food_dispatch")

db = db.getSiblingDB("food_dispatch")

db.createCollection("restaurants")
db.createCollection("delivery_services")

db.createCollection("drivers")

db.createCollection("consumers")

db.createCollection("orders")

var consumer_id = ObjectId()
var t1 = ObjectId()
var rating_id1 = ObjectId()
var rating_id2 = ObjectId()
var rating_id3 = ObjectId()
var coke_id = ObjectId()
var americano_id = ObjectId()

var delivery_service_id = ObjectId()
var driver_id = ObjectId()
var combo_id = ObjectId()

var order_id = ObjectId()
var order_id2 = ObjectId()

db.consumers.insertOne(
    {
        _id: consumer_id,
        first_name: "Pierre",
        name: "The Frenchie",
        orders: [order_id2],
        addresses: [
            {
                address_components: [
                    {
                        long_name: 'Ussel',
                        short_name: 'Ussel',
                        types: ['locality', 'political']
                    },
                    {
                        long_name: 'Correze',
                        short_name: 'Correze',
                        types: ['administrative_area_level_2', 'political']
                    },
                    {
                        long_name: 'Nouvelle-Aquitaine',
                        short_name: 'Nouvelle-Aquitaine',
                        types: ['administrative_area_level_1', 'political']
                    },
                    {
                        long_name: 'France',
                        short_name: 'FR',
                        types: ['country', 'political']
                    },
                    {
                        long_name: '19200',
                        short_name: '19200',
                        types: ['postal_code']
                    }
                ],
                geometry: {
                    location: {lat: 45.548905, lng: 2.313835},
                    viewport: {
                        south: 45.50605089319001,
                        west: 2.231891973590724,
                        north: 45.60142415049019,
                        east: 2.35602320081504
                    }
                },
                name: 'Ussel',
                html_attributions: []
            },
            {
                address_components: [
                    {
                        long_name: 'Ussel',
                        short_name: 'Ussel',
                        types: ['locality', 'political']
                    },
                    {
                        long_name: 'Correze',
                        short_name: 'Correze',
                        types: ['administrative_area_level_2', 'political']
                    },
                    {
                        long_name: 'Nouvelle-Aquitaine',
                        short_name: 'Nouvelle-Aquitaine',
                        types: ['administrative_area_level_1', 'political']
                    },
                    {
                        long_name: 'France',
                        short_name: 'FR',
                        types: ['country', 'political']
                    },
                    {
                        long_name: '19200',
                        short_name: '19200',
                        types: ['postal_code']
                    }
                ],
                geometry: {
                    location: {lat: 45.548905, lng: 2.313835},
                    viewport: {
                        south: 45.50605089319001,
                        west: 2.231891973590724,
                        north: 45.60142415049019,
                        east: 2.35602320081504
                    }
                },
                name: 'Ussel',
                html_attributions: []
            }],
        preferences: {
            likes: [],
            dislikes: [],
            allergens: []
        },
        email: "pierre@france.fr",
        credentials: {username: "Pierre", password: "A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ="}
    }
)

db.food_items.insertOne(
    {
        _id: coke_id,
        name: "Coca-Cola",
        price: 0.5,
        tags: [],
        tools_req: [],
        photo_url: "",
        ratings: [rating_id3]
    }
)

db.food_items.insertOne(
    {
        _id: americano_id,
        name: "The americano",
        price: 2.10,
        tags: [],
        tools_req: [],
        photo_url: "",
        ratings: []
    }
)

db.combos.insertOne({_id: combo_id, name: "Formula A", price: 6.2, tags: [], food_items: [coke_id, americano_id]})


db.restaurants.insertOne(
    {
        name: "Panini Land",
        food_items: [coke_id, americano_id],
        combos: [combo_id],
        ratings: [rating_id1],
        address: {
            address_components: [
                {
                    long_name: 'Ussel',
                    short_name: 'Ussel',
                    types: ['locality', 'political']
                },
                {
                    long_name: 'Correze',
                    short_name: 'Correze',
                    types: ['administrative_area_level_2', 'political']
                },
                {
                    long_name: 'Nouvelle-Aquitaine',
                    short_name: 'Nouvelle-Aquitaine',
                    types: ['administrative_area_level_1', 'political']
                },
                {
                    long_name: 'France',
                    short_name: 'FR',
                    types: ['country', 'political']
                },
                {
                    long_name: '19200',
                    short_name: '19200',
                    types: ['postal_code']
                }
            ],
            geometry: {
                location: {lat: 45.548905, lng: 2.313835},
                viewport: {
                    south: 45.50605089319001,
                    west: 2.231891973590724,
                    north: 45.60142415049019,
                    east: 2.35602320081504
                }
            },
            name: 'Ussel',
            html_attributions: []
        },
        tags: [t1],
        delivery_services: [delivery_service_id],
        orders: [order_id2],
        email: "panini.land@france.fr",
        credentials: {username: "panini", password: "A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ="},
    })

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
    name: "Star",
    ratings: [rating_id2],
    email: "under.rock@france.fr",
    credentials: {username: "patric", password: "A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ="},
    tools: [],
    current_pos: "",
    orders: []
})


db.orders.insertMany([{
    _id: order_id,
    items: [],
    total: 6.2,
    status: "complete",
    driver: driver_id,
    delivery_address: {
        address_components: [
            {
                long_name: 'Ussel',
                short_name: 'Ussel',
                types: ['locality', 'political']
            },
            {
                long_name: 'Correze',
                short_name: 'Correze',
                types: ['administrative_area_level_2', 'political']
            },
            {
                long_name: 'Nouvelle-Aquitaine',
                short_name: 'Nouvelle-Aquitaine',
                types: ['administrative_area_level_1', 'political']
            },
            {
                long_name: 'France',
                short_name: 'FR',
                types: ['country', 'political']
            },
            {
                long_name: '19200',
                short_name: '19200',
                types: ['postal_code']
            }
        ],
        geometry: {
            location: {lat: 45.548905, lng: 2.313835},
            viewport: {
                south: 45.50605089319001,
                west: 2.231891973590724,
                north: 45.60142415049019,
                east: 2.35602320081504
            }
        },
        name: 'Ussel',
        html_attributions: []
    },
    payment: {method: "card", type: "visa"}
}, {
    _id: order_id2,
    items: [],
    total: 6.2,
    status: "payed",
    driver: driver_id,
    delivery_address: {
        address_components: [
            {
                long_name: 'Ussel',
                short_name: 'Ussel',
                types: ['locality', 'political']
            },
            {
                long_name: 'Correze',
                short_name: 'Correze',
                types: ['administrative_area_level_2', 'political']
            },
            {
                long_name: 'Nouvelle-Aquitaine',
                short_name: 'Nouvelle-Aquitaine',
                types: ['administrative_area_level_1', 'political']
            },
            {
                long_name: 'France',
                short_name: 'FR',
                types: ['country', 'political']
            },
            {
                long_name: '19200',
                short_name: '19200',
                types: ['postal_code']
            }
        ],
        geometry: {
            location: {lat: 45.548905, lng: 2.313835},
            viewport: {
                south: 45.50605089319001,
                west: 2.231891973590724,
                north: 45.60142415049019,
                east: 2.35602320081504
            }
        },
        name: 'Ussel',
        html_attributions: []
    },
    payment: {method: "card", type: "visa"}
}])


db.restaurants.find({name: "Panini Land"}, {food_items: 1}).forEach(i => {
    db.orders.updateOne({_id: order_id}, {$push: {items: i}})
})


db.consumers.updateOne({_id: consumer_id}, {$push: {orders: order_id}})
db.restaurants.updateOne({name: "Panini Land"}, {$push: {orders: order_id}})
db.drivers.updateOne({_id: driver_id}, {$push: {orders: order_id}})

db.tags.insertOne({
    name: "vegeterian",
    type: "preference",
})

db.tags.insertOne({
    name: "peanuts",
    type: "allergen",
})

db.tags.insertMany([
    {name: "milk", type: "allergen"},
    {name: "eggs", type: "allergen"},
    {name: "wheat", type: "allergen"},
    {name: "soy", type: "allergen"},
    {name: "fish", type: "allergen"},
    {name: "shellfish", type: "allergen"},
    {name: "tree nuts", type: "allergen"},
    {name: "sesame", type: "allergen"},
    {name: "gluten", type: "allergen"}
])

db.tags.insertMany([
    {name: "chinese", type: "preference"},
    {name: "italian", type: "preference"},
    {name: "mexican", type: "preference"},
    {name: "indian", type: "preference"},
    {name: "japanese", type: "preference"},
    {name: "thai", type: "preference"},
    {name: "mediterranean", type: "preference"},
    {name: "french", type: "preference"},
    {name: "korean", type: "preference"},
    {name: "american", type: "preference"},
    {_id: t1, name: "sandwiches", type: "preference"}
])

db.tools.insertOne({
    name: "bike",
    type: "vehicle",
})

db.tools.insertOne({
    name: "van",
    type: "vehicle",
})

db.tools.insertOne({
    name: "freezer",
    type: "storage"
})

db.tools.insertOne({
    name: "container-plastic",
    type: "packaging"
})

db.ratings.insertMany([{
    _id: rating_id2,
    feedback: "All the food arrived soaked wet",
    value: 1,
    consumer_id: consumer_id
}, {
    _id: rating_id1,
    feedback: "Great Value",
    value: 5,
    consumer_id: consumer_id
}, {
    _id: rating_id3,
    feedback: "Overpriced",
    value: 2,
    consumer_id: consumer_id
}

])
