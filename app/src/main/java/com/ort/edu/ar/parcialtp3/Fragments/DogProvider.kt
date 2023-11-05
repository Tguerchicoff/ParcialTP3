package com.ort.edu.ar.parcialtp3.Fragments

import com.ort.edu.ar.parcialtp3.entities.Dog

class DogProvider {
    companion object{
        val dogList : MutableList<Dog> = mutableListOf(
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
                "Pato"

            ),
            Dog(
                "Negri",
                "Mestizo",
                "Pit",
                8,
                "Macho",
                "",
                16.0,
                "Bs. As.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/000_American_Pit_Bull_Terrier.jpg/320px-000_American_Pit_Bull_Terrier.jpg",
                "",
                "Pato"

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
                "Pato"

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
                "Pato"

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
                "Pato"

            )
        )
    }
}