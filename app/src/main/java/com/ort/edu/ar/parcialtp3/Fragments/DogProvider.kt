package com.ort.edu.ar.parcialtp3.Fragments

import com.ort.edu.ar.parcialtp3.entities.Dog

class DogProvider {
    companion object{

        fun getAllDogs(): List<Dog> {
            return dogList.filter{ !it.isAdopted}
        }

        fun getFavoriteDogs(): List<Dog> {
            return dogList.filter { it.isFavorite }
        }

        fun getAdoptedDogs(): List<Dog> {
            return dogList.filter { it.isAdopted }
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
                "Macho",
                "Mambo es un encantador perrito encontrado en la calle en malas condiciones. Luego de meses de veterinario esta 100% recuperado. Su pelaje suave como algodón y sus ojos tiernos te conquistarán al instante. Es un compañero leal y cariñoso que busca un hogar lleno de amor y cuidado. Le encanta pasear por el parque, jugar con su pelota y recibir caricias. Con su energía contagiosa y su naturaleza juguetona, teofrecerá compañía incondicional..",
                29.75,
                "Bs. As.",
                "https://scontent.faep9-1.fna.fbcdn.net/v/t31.18172-8/20643539_10213827216376555_7767922301574437407_o.jpg?_nc_cat=102&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeHrzWEM7dDjgBGrzI3ZPTcfxcWFn3mL7zHFxYWfeYvvMQHUhYrbBvTwtTo8aFt_b1c&_nc_ohc=dF-o0K6o4GwAX-3QY9F&_nc_ht=scontent.faep9-1.fna&oh=00_AfC-vYnFBG7CWRvyafWsj8YfOjID9Hit2hdDF-zppYbfjQ&oe=656DFDD5",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.18169-9/10670240_10205416238627368_6228580445516569846_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeGb8WklRiZmwIYbYw3bnYY3q4zgZCTb4OKrjOBkJNvg4oYTfqPj3ng1yti2yD-Z5aw&_nc_ohc=AnwuFqtPwpQAX973raE&_nc_ht=scontent.faep9-2.fna&oh=00_AfCQGek5xLvqkB6mkC4OXzH8UdN4jmaFZ_dCfP_dOOSvxA&oe=656E13F0",
                "Pato",
                null,
                null,
                true,
                true

            ),
            Dog(
                "Negri",
                "Mestizo",
                "Pit",
                8,
                "Macho",
                "Negri es un perrito valiente y fuerte que ha superado obstáculos en la vida con una determinación envidiable. A pesar de su historia, Negri es un compañero amigable y afectuoso que espera ansiosamente encontrar un hogar donde se sienta seguro y amado. Su mirada inteligente y su pelaje elegante hacen de Negri un canino especial. Le encanta explorar la naturaleza y disfrutar de largos paseos. Además, es un aprendiz rápido y estaría encantado de participar en actividades de entrenamiento.",
                16.0,
                "Bs. As.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/000_American_Pit_Bull_Terrier.jpg/320px-000_American_Pit_Bull_Terrier.jpg",
                "Max es un perro macho de raza Labrador Retriever con un pelaje dorado brillante que destaca por su amigable personalidad. Su pelaje es suave al tacto y brilla bajo el sol. Es un perro de tamaño mediano con una constitución robusta y patas fuertes. Max tiene unos ojos expresivos y marrones que reflejan su inteligencia y curiosidad. Siempre está lleno de energía y le encanta jugar a buscar la pelota. Es el compañero perfecto para largos paseos y aventuras al aire libre. Max es leal y cariñoso, y no hay nada que disfrute más que pasar tiempo con su familia.",
                "Pato",
                null,
                null,
                true,
                false
            ),
            Dog(
                "Mambo",
                "Mestizo",
                "Boyero de Berna",
                11 ,
                "Macho",
                "Mambo es una perrita elegante y carismática que cautiva a todos con su belleza y dulzura. Sus grandes ojos marrones y su pelaje suave son solo un reflejo de su personalidad encantadora. Bella es una compañera leal y amorosa que se adapta fácilmente a cualquier entorno. Es perfecta para familias, parejas o personas solteras. Le gusta pasar tiempo en casa, compartiendo momentos acogedores y relajantes. Bella se lleva bien con otros perros y es una excelente compañera de juegos. Darle un hogar a Bella es brindarle a tu vida una dosis de elegancia y alegría perruna.",
                29.75,
                "Bs. As.",
                "https://www.faunayaccion.com/img/animals/titan_perros_actores_perros_gigantes_40kg_80kg_boyero_de_berna_205268.jpeg?t=1696916528",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.18169-9/10670240_10205416238627368_6228580445516569846_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeGb8WklRiZmwIYbYw3bnYY3q4zgZCTb4OKrjOBkJNvg4oYTfqPj3ng1yti2yD-Z5aw&_nc_ohc=AnwuFqtPwpQAX973raE&_nc_ht=scontent.faep9-2.fna&oh=00_AfCQGek5xLvqkB6mkC4OXzH8UdN4jmaFZ_dCfP_dOOSvxA&oe=656E13F0",
                "Pato",
                null,
                null,
                false,
                false

            ),
            Dog(
                "Mambo",
                "Mestizo",
                "Boyero de Berna",
                11,
                "Macho",
                "Rocky es un perro macho de raza Pastor Alemán con un pelaje negro y marrón que le confiere un aspecto majestuoso. Su pelaje es espeso y suave, lo que lo hace destacar entre la multitud. Tiene una postura elegante y una mirada segura de sí misma. Rocky es un perro grande y poderoso con una increíble fuerza física y agilidad.",
                29.75,
                "Bs. As.",
                "https://cloudfront-us-east-1.images.arcpublishing.com/infobae/LC5MOYPX2JA25NY5H5KKMPREJI.jpg",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.18169-9/10670240_10205416238627368_6228580445516569846_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeGb8WklRiZmwIYbYw3bnYY3q4zgZCTb4OKrjOBkJNvg4oYTfqPj3ng1yti2yD-Z5aw&_nc_ohc=AnwuFqtPwpQAX973raE&_nc_ht=scontent.faep9-2.fna&oh=00_AfCQGek5xLvqkB6mkC4OXzH8UdN4jmaFZ_dCfP_dOOSvxA&oe=656E13F0",
                "Pato",
                null,
                null,
                false,
                true

            ),
            Dog(
                "Mambo",
                "Mestizo",
                "Boyero de Berna",
                11,
                "Macho",
                "",
                29.75,
                "Bs. As.",
                "https://scontent.faep9-1.fna.fbcdn.net/v/t31.18172-8/20643539_10213827216376555_7767922301574437407_o.jpg?_nc_cat=102&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeHrzWEM7dDjgBGrzI3ZPTcfxcWFn3mL7zHFxYWfeYvvMQHUhYrbBvTwtTo8aFt_b1c&_nc_ohc=dF-o0K6o4GwAX-3QY9F&_nc_ht=scontent.faep9-1.fna&oh=00_AfC-vYnFBG7CWRvyafWsj8YfOjID9Hit2hdDF-zppYbfjQ&oe=656DFDD5",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.18169-9/10670240_10205416238627368_6228580445516569846_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=4dc865&_nc_eui2=AeGb8WklRiZmwIYbYw3bnYY3q4zgZCTb4OKrjOBkJNvg4oYTfqPj3ng1yti2yD-Z5aw&_nc_ohc=AnwuFqtPwpQAX973raE&_nc_ht=scontent.faep9-2.fna&oh=00_AfCQGek5xLvqkB6mkC4OXzH8UdN4jmaFZ_dCfP_dOOSvxA&oe=656E13F0",
                "Pato",
                null,
                null,
                false,
                true
            )
        )
    }

}