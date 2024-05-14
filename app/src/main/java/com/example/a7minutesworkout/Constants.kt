package com.example.a7minutesworkout

object Constants {

    fun defalutExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false,


        )
        exerciseList.add(jumpingJacks)

        val highKneesRunning = ExerciseModel(
            2,
            "Hing Knees Running in Place",
            R.drawable.ic_high_knees_running,
            false,
            false,


            )
        exerciseList.add(highKneesRunning)

        val lunge = ExerciseModel(
            3,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false,


            )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            4,
            "Plank",
            R.drawable.ic_plank,
            false,
            false,


            )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            5,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false,


            )
        exerciseList.add(pushUp)

        val pushUpAndRotaion = ExerciseModel(
            6,
            "Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false,


            )
        exerciseList.add(pushUpAndRotaion)

        val sidePlank = ExerciseModel(
            7,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false,


            )
        exerciseList.add(sidePlank)

        val stepOnChair = ExerciseModel(
            8,
            "Step On Chair",
            R.drawable.ic_step_on_chair,
            false,
            false,


            )
        exerciseList.add(stepOnChair)

        val tricepDipOnChair = ExerciseModel(
            9,
            "Tricep Dip on Chair",
            R.drawable.ic_tricep_dip_on_chair,
            false,
            false,


            )
        exerciseList.add(tricepDipOnChair)

        val wallSit = ExerciseModel(
            10,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false,


            )
        exerciseList.add(wallSit)

        val abdominalCrunch = ExerciseModel(
            11,
            "Abdominal Crunsh",
            R.drawable.ic_abdominal_crunch,
            false,
            false,


            )
        exerciseList.add(abdominalCrunch)
        val squat = ExerciseModel(
            12,
            "Squat",
            R.drawable.ic_squat,
            false,
            false,


            )
        exerciseList.add(squat)





        return exerciseList
    }
}