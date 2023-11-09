package com.ort.edu.ar.parcialtp3.Fragments

import com.ort.edu.ar.parcialtp3.entities.Dog
import com.ort.edu.ar.parcialtp3.entities.Provinces

class DogProvider {
    companion object {

        fun filterDogs(gender: String?, province: String?, breed: String?, age: Int?): List<Dog> {
            // Inicialmente, todos los perros están en la lista de resultados
            var filteredDogs = getAllDogs().toList()

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

            if (age != null) {
                //Filtrar por edad
                filteredDogs = filteredDogs.filter { it.age == age }
            }

            return filteredDogs
        }

        fun getAllDogs(): List<Dog> {
            return dogList.filter { !it.isAdopted }
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

        fun getLocations(): List<String> {
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

        val dogList: MutableList<Dog> = mutableListOf(
            Dog(
                "Mambo",
                "Akita",
                "Pura",
                11,
                "Hembra",
                "Mambo es una encantadora perrita encontrada en la calle en malas condiciones. Luego de meses de veterinario esta 100% recuperada. Su pelaje suave como algodón y sus ojos tiernos te conquistarán al instante. Es una compañera leal y cariñosa que busca un hogar lleno de amor y cuidado. Le encanta pasear por el parque, jugar con su pelota y recibir caricias. Con su energía contagiosa y su naturaleza juguetona, teofrecerá compañía incondicional.",
                29.75,
                "BUENOS_AIRES",
                "https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpg",
                "https://images.dog.ceo/breeds/akita/Akita_inu_blanc.jpg",
                "https://images.dog.ceo/breeds/akita/An_Akita_Inu_resting.jpg",
                null,
                "Pato",
                false,
                false

            ),
            Dog(
                "Aquiles",
                "Vizsla",
                "Puro",
                8,
                "Macho",
                "Aquiles es un perro valiente y fuerte que ha superado obstáculos en la vida con una determinación envidiable. A pesar de su historia, Aki es un compañero amigable y afectuoso que espera ansiosamente encontrar un hogar donde se sienta seguro y amado. Su mirada inteligente y su pelaje elegante hacen de Aki un canino especial. Le encanta explorar la naturaleza y disfrutar de largos paseos. Además, es un aprendiz rápido y estaría encantado de participar en actividades de entrenamiento.",
                20.0,
                "CORDOBA",
                "https://images.dog.ceo/breeds/vizsla/n02100583_9922.jpg",
                "https://images.dog.ceo/breeds/vizsla/n02100583_12904.jpg",
                "https://images.dog.ceo/breeds/vizsla/n02100583_7467.jpg",
                null,
                "Fede",
                false,
                false
            ),
            Dog(

                "Pepe",
                "Bullterrier",
                "Staffordshire",
                9,
                "Pepe",
                "Pepe es un majestuoso y carismático perro que cautiva a todos con su belleza y encanto. Sus profundos ojos oscuros y su pelaje suave son solo un reflejo de su personalidad encantadora. Pepe es un compañero leal y cariñoso que se adapta fácilmente a cualquier entorno. Es perfecto para familias, parejas o personas solteras. Disfruta de pasar tiempo en casa, compartiendo momentos acogedores y relajantes. Pepe se lleva bien con otros perros y es un excelente compañero de juegos. Darle un hogar a Pepe es agregar a tu vida una dosis de energía y alegría perruna.",
                31.5,
                "BUENOS_AIRES",
                "https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_4058.jpg",
                "https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_1275.jpg",
                "https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_1371.jpg",
                null,
                "Joaquin",
                false,
                false

            ),
            Dog(
                "Tequila",
                "Chihuahua",
                "Toy",
                7,
                "Hembra",
                "Tequila es una adorable chihuahua con un pelaje suave y sedoso que varía en tonos de marrón y blanco, lo que le confiere una apariencia encantadora y juguetona. A pesar de su pequeño tamaño, Tequila tiene una personalidad valiente y llena de energía. Siempre está lista para explorar el mundo que la rodea y tiene una mirada curiosa y traviesa en sus ojos brillantes. Tequila es una chihuahua encantadora y cariñosa que roba corazones con su simpatía y alegría.",
                5.0,
                "BUENOS_AIRES",
                "https://images.dog.ceo/breeds/chihuahua/n02085620_13151.jpg",
                "https://images.dog.ceo/breeds/chihuahua/n02085620_3763.jpg",
                "https://images.dog.ceo/breeds/chihuahua/black_chihuahua.jpg",
                null,
                "Jose",
                false,
                false

            ),
            Dog(
                "Max",
                "Pastor",
                "Caucásico",
                2,
                "Macho",
                "Max es un perro macho de raza Pastor caucásito que destaca por su amigable personalidad. Su pelaje es suave al tacto y brilla bajo el sol. Es un perro de tamaño grande con una constitución robusta y patas fuertes. Max tiene unos ojos expresivos y marrones que reflejan su inteligencia y curiosidad. Siempre está lleno de energía y le encanta jugar a buscar la pelota. Es el compañero perfecto para largos paseos y aventuras al aire libre. Max es leal y cariñoso, y no hay nada que disfrute más que pasar tiempo con su familia.",
                42.0,
                "MENDOZA",
                "https://images.dog.ceo/breeds/ovcharka-caucasian/IMG_20190826_112025.jpg",
                "https://images.dog.ceo/breeds/ovcharka-caucasian/IMG_20200201_145256.jpg",
                "https://images.dog.ceo/breeds/ovcharka-caucasian/IMG_20191105_141904.jpg",
                null,
                "Pia",
                false,
                false
            ),
            Dog(
                "Sasha",
                "Spaniel",
                "Cocker",
                5,
                "Hembra",
                "Sasha, una Spaniel Cocker de 5 años, cautiva con su pelaje rizado. Su mirada tierna y afectuosa ilumina cada día, brindando alegría a todos a su alrededor.",
                10.0,
                "BUENOS_AIRES",
                "https://images.dog.ceo/breeds/spaniel-cocker/n02102318_12613.jpg",
                "https://images.dog.ceo/breeds/spaniel-cocker/n02102318_7327.jpg",
                "https://images.dog.ceo/breeds/spaniel-cocker/n02102318_13800.jpg",
                null,
                "Gonza",
                false,
                false
            ),
            Dog(
                "Cuba",
                "Golden",
                "Retriever",
                7,
                "Hembra",
                "Cuba, una fiel compañera de vida. Con su mirada curiosa y sus ganas de jugar, no hay dudas que si adoptas a Cuba, adoptas a tu nueva mejor amiga.",
                32.0,
                "BUENOS_AIRES",
                "https://images.dog.ceo/breeds/retriever-golden/n02099601_7930.jpg",
                "https://images.dog.ceo/breeds/retriever-golden/n02099601_2796.jpg",
                "https://images.dog.ceo/breeds/retriever-golden/20200731_180910_200731.jpg",
                null,
                "Agus",
                false,
                false
            ),
            Dog(
            "Duncan",
            "Braco",
            "Aleman",
            9,
            "Macho",
            "Duncan, un compañero leal y aventurero. Su espíritu valiente y su disposición para explorar te llevarán en emocionantes travesías. Adoptar a Duncan es el primer paso para una vida repleta de aventuras y lealtad inquebrantable.",
            21.0,
            "ENTRE_RIOS",
            "https://images.dog.ceo/breeds/pointer-german/n02100236_5647.jpg",
            "https://images.dog.ceo/breeds/pointer-german/n02100236_5597.jpg",
            "https://images.dog.ceo/breeds/pointer-german/n02100236_3629.jpg",
            null,
            "Tomi",
            false,
            false
        ),
            Dog(
                "Truman",
                "Boyero",
                "De Berna",
                10,
                "Macho",
                "Truman, un gigante amigable y protector. Con su gran corazón y su naturaleza cariñosa, Truman es un compañero ideal para aquellos que buscan ternura y seguridad. Cuando adoptas a Truman, te conviertes en su familia y él en tu defensor inquebrantable.",
                40.0,
                "CATAMARCA",
                "https://images.dog.ceo/breeds/mountain-bernese/n02107683_4520.jpg",
                "https://images.dog.ceo/breeds/mountain-bernese/n02107683_1536.jpg",
                "https://images.dog.ceo/breeds/mountain-bernese/n02107683_33.jpg",
                null,
                "Martin",
                false,
                false
            ),
            Dog(
                "Luna",
                "Border",
                "Terrier",
                8,
                "Hembra",
                "Luna, una Border Terrier con un espíritu intrépido y un corazón valiente. Su curiosidad inagotable y su determinación incansable la convierten en la compañera ideal para cualquier aventura. Si buscas un amigo leal y valiente para explorar el mundo a tu lado, Luna es la elección perfecta. Con ella, cada día es una nueva y emocionante aventura esperando a ser descubierta..",
                5.0,
                "FORMOSA",
                "https://images.dog.ceo/breeds/terrier-border/n02093754_909.jpg",
                "https://images.dog.ceo/breeds/terrier-border/n02093754_1247.jpg",
                "https://images.dog.ceo/breeds/terrier-border/n02093754_7820.jpg",
                null,
                "Pato",
                false,
                false
            )
            ,
            Dog(
                "Toto",
                "Lobero",
                "Irlandes",
                6,
                "Macho",
                "Toto, un majestuoso Lobero Irlandés que irradia elegancia y nobleza. Con su imponente presencia y su carácter amable, es un verdadero caballero de cuatro patas. Toto es el compañero perfecto para quienes buscan la compañía de un gentil gigante. Su lealtad inquebrantable y su amor eterno harán que te sientas especial todos los días. Si anhelas la compañía de un verdadero aristócrata canino, Toto es la elección ideal.",
                56.0,
                "TUCUMAN",
                "https://images.dog.ceo/breeds/wolfhound-irish/n02090721_626.jpg",
                "https://images.dog.ceo/breeds/wolfhound-irish/n02090721_2116.jpg",
                "https://images.dog.ceo/breeds/wolfhound-irish/n02090721_3109.jpg",
                null,
                "Joaquin",
                false,
                false
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