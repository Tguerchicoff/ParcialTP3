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
                Dog.Gender.male,
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
                Dog.Gender.female,
                "",
                16.0,
                "Bs. As.",
                "https://scontent.faep9-1.fna.fbcdn.net/v/t39.30808-6/267966187_10229124052342951_5201137882717791407_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeGovTf5C6vOSFO52GSgfZC-39oxI5kPxQ7f2jEjmQ_FDlwSwt7L6N7jQPiM03eR6cM&_nc_ohc=hHqrUEV4EN8AX8EITRp&_nc_ht=scontent.faep9-1.fna&oh=00_AfBb8LrB-7EGZVN2Y1ACmq-S8aHwddrfnbLVhyWrJHd53A&oe=654C7F94",
                "",
                "Pato"

            ),
            Dog(
                "Mambo",
                "Mestizo",
                "Boyero de Berna",
                11,
                Dog.Gender.male,
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
                Dog.Gender.male,
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
                Dog.Gender.male,
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