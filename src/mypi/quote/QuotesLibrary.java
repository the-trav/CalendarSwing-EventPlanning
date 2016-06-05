/*
UPDATE THIS CLASS TO ALLOW REMOVAL OF QUOTES ONCE THERE SELECTED
ONCE THE CLASS REACHES 0 THAN RE INTIALIZE THE CLASS 
 */
package mypi.quote;

import java.util.ArrayList;

/**
 *
 * class that continues quotes and the author of those quotes
 * it is the programmers responsibility to make sure the list and authors stay in the exact order as they are
 * also it is important for the programmer to add to this list manually
 */
public class QuotesLibrary extends Quotes{

//private ArrayList<String> quoteArrayList = new 
private final String[] QUOTE_LIST={
                "The happiest people are those who are too busy to notice wether they are or not",
                "Life is about making an impact, not making an income",
		"What ever the mind of man can conceive and believe, it can achieve",
		"I attribute my success to this: I never gave or took any excuse",
		"You miss 100% of the shots you don't take",
		"The most difficult thing is the decision to act, the rest is merely tenacity.",
		"Every strike brings me closer to the next homerun.",
		"The mind is everything, What you think you become.",
		"Eighty percent of success is showing up",
		"Your time is limited, so dont waste it living someone else's life",
		"Whether you think you can or you think you can't, You're Right",
		"The two most important days in your life are the day you are born and the day you find out why.",
		"Either you run the day, or the day runs you",
		"I have not failed, i've just found 10,000 ways that won't work",
		"Logic will get you from A to B. Imagination will take you anywhere",
		"When i stand before God at the end of my life, I would hope that i would not have a single bit of talent left and could say, I used everything you gave me.",
		"I love you Colleen Meredith Cull",
		"Believe you can and you're halfway there.",
		"Everything you've ever wanted is on the other side of fear",
		"We can easily forgive a child who is afraid of the dark; the real tragedy of life is when men are afraid of the light",
		"Limitations live only in our minds. But if we use our imaginations, Our possibilities become limitless",
		"In order to succeed, your desire for success should be greater than your fear of failure",
		"The person who says it connot be done should not interrupt the person who is doing",
		"A person who never  made a mistake never tried anything new",
		"I would rather die of passion than of boredom",
		"Build your own dreams, or someone else will hire you to build theirs",
		"It does not matter how slowly you go, as long as you do not stop",
		"If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough",
		"Never give up on a dream just because of the time it will take to accomplish it. The time will pass anyway",
		"Our lives begin to end the day we become silent about things that matter",
		"If you do what you've always done, you'll get what you've always gotten",
		"The question isnt who is going to let me; it's who is going to stop me",
		"When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.",
		"Either write something worth reading or do something worth writing.",
		"Give me six hours to chop down a tree and i will spend the first four sharpening the axe",
		"Be Somebody who makes everybody feel like a somebody",
                "Junk Food You've craved for an hour or the body you've craved for a lifetime?",
                "When everything seems like its falling aparat thats when god is putting things together",
                "It's the possibility of having a dream come true that makes life interesting",
                "The best way to get things done is to simply begin",
                "The darkest nights produce the brighest stars",
                "You have to believe in yourself when no one else does - that makes you a winner right there",
                "Dont let schooling interfere with your education",
                "Be happy with what you have while working for what you want.",
                "What defines us is how well we rise after falling",
                "When life puts you in tough situations dont say \"Why Me\" say \"Try me\" ",
                "Suck it up for one day you dont have to suck it in",
                "Those who do not move, do not notice their chains",
                "Is what you're doing today getting you closer to where you want to be tommorow?",
                "Stive for progress not perfection",
                "No matter how slow your progress, you are still ahead of everyone who isn't trying",
                "Dreams don't work unless you do",
                "Being negative only makes a difficult journey more difficult",
                "Doubt kills more dreams than failure ever will",
                "Don't limit your challenges, challenge your limits",
                "A negative mind will never give you a positive life",
                "If you change the way you look at things, the things you look at change",
                "If you look for bad you will find it, if you look for good,you will find it.We always have a choice.",
                "Dont put the key to your happiness in someone elses pocket.",
                "Instead of saying i dont have time try saying its not a priority and see how that feels",
                "Notice that stiffest tree is most easily cracked, while the bamboo survives by bending with the wind",
                "A river cuts through a rock not because of its power but its persistence",
                
        
    };
	

    
    @Override
    public String getQuote(int index) {
        return QUOTE_LIST[index];
    }

    @Override
    public int getLibrarySize() {
        return QUOTE_LIST.length;
    }



    
}
