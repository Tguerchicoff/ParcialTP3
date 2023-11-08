package com.ort.edu.ar.parcialtp3.Fragments

import com.ort.edu.ar.parcialtp3.entities.Dog
import com.ort.edu.ar.parcialtp3.entities.Provinces

class DogProvider {
    companion object{

        fun filterDogs(gender: String?, province: String?, breed: String?, age: Int?): List<Dog> {
            // Inicialmente, todos los perros están en la lista de resultados
            var filteredDogs = getAllDogs() .toList()

            if (gender != null) {
                // Filtrar por género
                filteredDogs = filteredDogs.filter { it.gender == gender }
            }

            if (province != null) {
                // Filtrar por provincia
                filteredDogs = filteredDogs.filter { it.location == province }
            }

            if (breed != null) {
                // Filtrar por raza
                filteredDogs = filteredDogs.filter { it.breed == breed }
            }

            if(age != null){
                //Filtrar por edad
                filteredDogs = filteredDogs.filter { it.age == age }
            }

            return filteredDogs
        }

        fun getAllDogs(): List<Dog> {
            return dogList.filter{ !it.isAdopted}
        }

        fun getFavoriteDogs(): List<Dog> {
            return dogList.filter { it.isFavorite }
        }

        fun getAdoptedDogs(): List<Dog> {
            return dogList.filter { it.isAdopted }
        }

        fun addDog(dog: Dog) {
            dogList.add(dog)
        }

        fun getLocations(): List<String>{
            return Provinces.values().map { it.toString() }
        }

        fun removeFromAllDogs(dog: Dog) {
            val index = dogList.indexOf(dog)
            if (index != -1) {
                dogList.removeAt(index)
            }
        }

        fun toggleFavoriteStatus(dog: Dog) {
            val index = dogList.indexOf(dog)
            if (index != -1) {
                val updatedDog = dogList[index].copy(isFavorite = !dog.isFavorite)
                dogList[index] = updatedDog
            }
        }

        fun toggleAdoptedStatus(dog: Dog) {
            val index = dogList.indexOf(dog)
            if (index != -1) {
                val updatedDog = dogList[index].copy(isAdopted = !dog.isAdopted)
                dogList[index] = updatedDog
            }
        }

        val dogList : MutableList<Dog> = mutableListOf(
            Dog(
                "Mambo",
                "Mestizo",
                "Boyero de Berna",
                11,
                "Hembra",
                "Mambo es un encantador perrito encontrado en la calle en malas condiciones. Luego de meses de veterinario esta 100% recuperado. Su pelaje suave como algodón y sus ojos tiernos te conquistarán al instante. Es un compañero leal y cariñoso que busca un hogar lleno de amor y cuidado. Le encanta pasear por el parque, jugar con su pelota y recibir caricias. Con su energía contagiosa y su naturaleza juguetona, teofrecerá compañía incondicional..",
                29.75,
                "Bs. As.",
                "https://scontent.faep9-1.fna.fbcdn.net/v/t31.18172-8/20643539_10213827216376555_7767922301574437407_o.jpg?_nc_cat=102&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeHrzWEM7dDjgBGrzI3ZPTcfxcWFn3mL7zHFxYWfeYvvMQHUhYrbBvTwtTo8aFt_b1c&_nc_ohc=dF-o0K6o4GwAX-3QY9F&_nc_ht=scontent.faep9-1.fna&oh=00_AfC-vYnFBG7CWRvyafWsj8YfOjID9Hit2hdDF-zppYbfjQ&oe=656DFDD5",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.18169-9/10670240_10205416238627368_6228580445516569846_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeGb8WklRiZmwIYbYw3bnYY3q4zgZCTb4OKrjOBkJNvg4oYTfqPj3ng1yti2yD-Z5aw&_nc_ohc=AnwuFqtPwpQAX973raE&_nc_ht=scontent.faep9-2.fna&oh=00_AfCQGek5xLvqkB6mkC4OXzH8UdN4jmaFZ_dCfP_dOOSvxA&oe=656E13F0",
                "https://scontent.faep9-1.fna.fbcdn.net/v/t31.18172-8/15540966_10211372975062056_5366359715840530816_o.jpg?_nc_cat=103&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeHRT7gj7KSVWhUXp7y7ujRQJJRciU6WpOcklFyJTpak56XCHoCZTpAokOP6plO2A4U&_nc_ohc=9cCE6CP9waQAX8aNgrh&_nc_ht=scontent.faep9-1.fna&oh=00_AfC14VNhkyRTgr-O2nqcd3oTj10OK2eiCdCoipredk6-LQ&oe=657227D2",
                null,
                "Pato",
                true,
                true

            ),
            Dog(
                "Akilez",
                "Mestizo",
                "Pit",
                8,
                "Macho",
                "Aki es un perrito valiente y fuerte que ha superado obstáculos en la vida con una determinación envidiable. A pesar de su historia, Aki es un compañero amigable y afectuoso que espera ansiosamente encontrar un hogar donde se sienta seguro y amado. Su mirada inteligente y su pelaje elegante hacen de Negri un canino especial. Le encanta explorar la naturaleza y disfrutar de largos paseos. Además, es un aprendiz rápido y estaría encantado de participar en actividades de entrenamiento.",
                20.0,
                "Cordoba",
                "https://images.unsplash.com/photo-1670752826047-5689584dfa93?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1660760610164-24a2cf0824a1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1660760609460-a682c6339dfb?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                null,
                "Fede",
                true,
                false
            ),
            Dog(

                "Pepe",
                "Labrador",
                "Rubio",
                9 ,
                "Macho",
                "Mambo es una perrita elegante y carismática que cautiva a todos con su belleza y dulzura. Sus grandes ojos marrones y su pelaje suave son solo un reflejo de su personalidad encantadora. Bella es una compañera leal y amorosa que se adapta fácilmente a cualquier entorno. Es perfecta para familias, parejas o personas solteras. Le gusta pasar tiempo en casa, compartiendo momentos acogedores y relajantes. Bella se lleva bien con otros perros y es una excelente compañera de juegos. Darle un hogar a Bella es brindarle a tu vida una dosis de elegancia y alegría perruna.",
                31.5,
                "Bs. As.",
                "https://images.unsplash.com/photo-1685614016777-6ee6a1836736?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1531486613315-3671081d848f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8bGFicmFkb3IlMjBydWJpb3xlbnwwfHwwfHx8MA%3D%3D",
                "https://images.unsplash.com/photo-1632434877596-4982d917d7a0?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                null,
                "Joaquin",
                false,
                false

            ),
            Dog(
                "Tequila",
                "Chihuahua",
                "toy",
                7,
                "Hembra",
                "Tequila es una adorable chihuahua con un pelaje suave y sedoso que varía en tonos de marrón y blanco, lo que le confiere una apariencia encantadora y juguetona. A pesar de su pequeño tamaño, Tequila tiene una personalidad valiente y llena de energía. Siempre está lista para explorar el mundo que la rodea y tiene una mirada curiosa y traviesa en sus ojos brillantes. Tequila es una chihuahua encantadora y cariñosa que roba corazones con su simpatía y alegría.",
                5.0,
                "Bs. As.",
                "https://images.unsplash.com/photo-1610041518868-f9284e7eecfe?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1494205577727-d32e58564756?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D656E13F0",
                "https://images.unsplash.com/photo-1514134136604-e14631dd3477?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                null,
                "Jose",
                false,
                true

            ),
            Dog(
                "Max",
                "Labrador",
                "Retriever",
                2,
                "Macho",
                "Max es un perro macho de raza Labrador Retriever con un pelaje dorado brillante que destaca por su amigable personalidad. Su pelaje es suave al tacto y brilla bajo el sol. Es un perro de tamaño mediano con una constitución robusta y patas fuertes. Max tiene unos ojos expresivos y marrones que reflejan su inteligencia y curiosidad. Siempre está lleno de energía y le encanta jugar a buscar la pelota. Es el compañero perfecto para largos paseos y aventuras al aire libre. Max es leal y cariñoso, y no hay nada que disfrute más que pasar tiempo con su familia.",
                17.0,
                "Mendoza",
                "https://images.unsplash.com/photo-1623052940978-051d2c0fb4be?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1552575595-38bfbd75841a?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1602941953280-ea523175bcfc?q=80&w=1942&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                null,
                "Pia",
                false,
                true
            ),
            Dog(
                "Sasha",
                "australian labradoodle",
                "",
                5,
                "Hembra",
                "Sasha, una Australian Labradoodle de 5 años, cautiva con su pelaje rizado en tonos crema y chocolate. Su mirada tierna y afectuosa ilumina cada día, brindando alegría a todos a su alrededor.",
                10.0,
                "Bs. As.",
                "https://images.unsplash.com/photo-1659875459526-172953ed6dba?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8YXVzdHJhbGlhbiUyMGxhYnJhZG9vZGxlfGVufDB8fDB8fHww",
                "https://images.unsplash.com/photo-1663416771563-4c2342b62551?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8YXVzdHJhbGlhbiUyMGxhYnJhZG9vZGxlfGVufDB8fDB8fHww",
                "https://images.unsplash.com/photo-1659875459322-43b970c6642e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YXVzdHJhbGlhbiUyMGxhYnJhZG9vZGxlfGVufDB8fDB8fHww",
                null,
                "Gonza",
                false,
                true
            )
        )
        val locationsList: MutableList<String> = mutableListOf(
            "CABA",
            "Buenos Aires, Zona Norte",
            "Buenos Aires, Zona Sur",
            "Buenos Aires, Zona Oeste"
        )
    }



}