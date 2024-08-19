package com.john.islamiv2.OnBoarding

import com.john.islamiv2.R

data class OnBoardingData(val image:Int , val title:String , val description:String){

    companion object {
        fun getOnBoardingData():MutableList<OnBoardingData>{
            return mutableListOf(
                OnBoardingData(R.drawable.img_onboarding_1 , "Chose Language" , ""),
                OnBoardingData(R.drawable.img_onboarding_2 , "Welcome To Islami" , "We Are Very Excited To Have You In Our Community"),
                OnBoardingData(R.drawable.img_onboarding_3 , "Reading the Quran" , "Read, and your Lord is the Most Generous"),
                OnBoardingData(R.drawable.img_onboarding_4 , "Bearish" , "Praise the name of your Lord, the Most High"),
                OnBoardingData(R.drawable.img_onboarding_5 , "Holy Quran Radio" , "You can listen to the Holy Quran Radio through the application for free and easily"),
            )
        }
    }

}
