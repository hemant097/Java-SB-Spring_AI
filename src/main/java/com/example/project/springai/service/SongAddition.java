package com.example.project.springai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SongAddition {

    private final VectorStore vectorStore;

    public void ingestLyricsToVS(){

        List<Document> songsList = List.of(
                new Document(
                        """
                        It might seem crazy what I am 'bout to say
                        Sunshine, she's here you can take a break
                        I'ma hot air balloon that could go to space, huh
                        With the air, like I don't care, baby, by the way, huh
                        Clap along if you feel like a room without a roof
                        (Because I'm happy)
                        Clap along if you feel like happiness is the truth
                        (Because I'm happy)
                        Clap along if you know what happiness is to you
                        (Because I'm happy)
                        Clap along if you feel like that's what you wanna do
                        Here come bad news, talking this and that (talk, yeah)
                        Well, give me all you got and don't hold back (yeah)
                        Well, I should probably warn you, I'll be just fine (yeah)
                        No offense to you, don't waste your time, here's why
                        Clap along if you feel like a room without a roof
                        (Because I'm happy)
                        Clap along if you feel like happiness is the truth
                        (Because I'm happy)
                        Clap along if you know what happiness is to you
                        (Because I'm happy)
                        Clap along if you feel like that's what you wanna do (hey, c'mon, uh)
                        bring me down, can't nothin'
                        (Happy) bring me down, my level's too high
                        (Happy) to bring me down, can't nothin' (huh)
                        (Happy) bring me down, I said (let me tell you now), uh
                        can't nothin', uh
                        (Happy, happy), bring me down (happy, happy), my level's too high
                        (Happy, happy) to bring me down (happy, happy), can't nothin'
                        (Happy, happy) bring me down, I said
                        Clap along if you feel like a room without a roof
                        (Because I'm happy)
                        Clap along if you feel like happiness is the truth
                        (Because I'm happy)
                        Clap along if you know what happiness is to you
                        (Because I'm happy)
                        Clap along if you feel like that's what you wanna do
                        Clap along if you feel like a room without a roof
                        (Because I'm happy)
                        Clap along if you feel like happiness is the truth
                        (Because I'm happy)
                        Clap along if you know what happiness is to you
                        (Because I'm happy)
                        Clap along if you feel like (uh) that's what you wanna do (hey, c'mon, uh)
                        , can't nothin'
                        (Happy, happy) uh, bring me down (happy, happy) my level's too high
                        (Happy, happy) to bring me down (happy, happy), can't nothin'
                        (Happy, happy) bring me down, I said
                        Clap along if you feel like a room without a roof
                        (Because I'm happy)
                        Clap along if you feel like happiness is the truth
                        (Because I'm happy)
                        Clap along if you know what happiness is to you, ay, ay, ay
                        (Because I'm happy)
                        Clap along if you feel like that's what you wanna do
                        Clap along if you feel like a room without a roof
                        (Because I'm happy)
                        Clap along if you feel like happiness is the truth
                        (Because I'm happy)
                        Clap along if you know what happiness is to you (hey)
                        (Because I'm happy)
                        Clap along if you feel like that's what you wanna do
                        Huh-huh, come on
                        """,
                        Map.of("artist", "Pharrell Williams", "genre", "Pop", "year", 2013, "theme", "Joy and positivity")
                ),
                new Document(
                        """
                                I heard that you're settled down
                                That you found a girl and you're married now
                                I heard that your dreams came true
                                Guess she gave you things I didn't give to you
                                Old friend, why are you so shy?
                                Ain't like you to hold back or hide from the light
                                I hate to turn up out of the blue, uninvited
                                But I couldn't stay away, I couldn't fight it
                                I had hoped you'd see my face
                                And that you'd be reminded that for me it isn't over
                                Never mind, I'll find someone like you
                                I wish nothing but the best for you two
                                Don't forget me, I beg
                                I remember you said
                                Sometimes it lasts in love
                                But sometimes it hurts instead
                                Sometimes it lasts in love
                                But sometimes it hurts instead
                                You know how the time flies
                                Only yesterday was the time of our lives
                                We were born and raised in a summer haze
                                Bound by the surprise of our glory days
                                I hate to turn up out of the blue, uninvited
                                But I couldn't stay away, I couldn't fight it
                                I had hoped you'd see my face
                                And that you'd be reminded that for me it isn't over
                                Never mind, I'll find someone like you
                                I wish nothing but the best for you two
                                Don't forget me, I beg
                                I remember you said
                                Sometimes it lasts in love
                                But sometimes it hurts instead
                                Nothing compares, no worries or cares
                                Regrets and mistakes, they're in memories made
                                Who would have known how bittersweet this would taste?
                                Never mind, I'll find someone like you
                                I wish nothing but the best for you two
                                Don't forget me, I beg
                                I remember you said
                                Sometimes it lasts in love
                                But sometimes it hurts instead
                                Never mind, I'll find someone like you
                                I wish nothing but the best for you two
                                Don't forget me, I beg
                                I remember you said
                                Sometimes it lasts in love
                                But sometimes it hurts instead
                                Sometimes it lasts in love
                                But sometimes it hurts instead
                                """,
                        Map.of("artist", "Adele", "genre", "Pop/Soul", "year", 2011, "theme", "Heartbreak and acceptance")
                ),
                new Document(
                        """
                                Doh
                                Doh doh doh, doh doh doh, doh doh
                                Doh doh doh, doh doh doh, doh doh
                                Doh doh doh, doh doh doh, doh doh
                                Doh doh doh, doh duh (Aaaaaaow!)
                                
                                
                                This hit
                                That ice cold
                                Michelle Pfeiffer
                                That white gold
                                This one for them hood girls
                                Them good girls
                                Straight masterpieces
                                Stylin', wiling
                                Livin' it up in the city
                                Got Chucks on with Saint Laurent
                                Gotta kiss myself I'm so pretty
                                
                                
                                I'm too hot (hot damn)
                                Call the police and the fireman
                                I'm too hot (hot damn)
                                Make a dragon wanna retire, man
                                I'm too hot (hot damn)
                                Say my name you know who I am
                                I'm too hot (hot damn)
                                And my band 'bout that money
                                Break it down...
                                
                                
                                Girls hit your hallelujah (ooh)
                                Girls hit your hallelujah (ooh)
                                Girls hit your hallelujah (ooh)
                                'Cause uptown funk gon' give it to you
                                'Cause uptown funk gon' give it to you
                                'Cause uptown funk gon' give it to you
                                Saturday night and we in the spot
                                Don't believe me just watch (Come on)
                                
                                
                                Doh
                                Doh doh doh, doh doh doh, doh doh (Hah!)
                                
                                
                                Don't believe me just watch
                                
                                
                                Doh
                                Doh doh doh, doh doh doh, doh doh (Hah!)
                                
                                
                                Don't believe me just watch
                                Don't believe me just watch
                                Don't believe me just watch
                                Don't believe me just watch
                                Hey, hey, hey, oh!
                                
                                
                                Stop
                                Wait a minute
                                Fill my cup put some liquor in it
                                Take a sip, sign the check
                                Julio, get the stretch!
                                Ride to Harlem, Hollywood, Jackson, Mississippi
                                If we show up, we gon' show out
                                Smoother than a fresh jar of Skippy
                                
                                
                                I'm too hot (hot damn)
                                Call the police and the fireman
                                I'm too hot (hot damn)
                                Make a dragon wanna retire, man
                                I'm too hot (hot damn, hot damn)
                                Bitch, say my name you know who I am
                                I'm too hot (hot damn)
                                And my band 'bout that money
                                Break it down...
                                
                                
                                Girls hit your hallelujah (ooh)
                                Girls hit your hallelujah (ooh)
                                Girls hit your hallelujah (ooh)
                                'Cause uptown funk gon' give it to you
                                'Cause uptown funk gon' give it to you
                                'Cause uptown funk gon' give it to you
                                Saturday night and we in the spot
                                Don't believe me just watch (come on)
                                
                                
                                Doh
                                Doh doh doh, doh doh doh, doh doh (Hah!)
                                
                                
                                Don't believe me just watch
                                
                                
                                Doh
                                Doh doh doh, doh doh doh, doh doh (Hah!)
                                
                                
                                Don't believe me just watch
                                Don't believe me just watch
                                Don't believe me just watch
                                Don't believe me just watch
                                Hey, hey, hey, oh!
                                
                                
                                Before we leave
                                Let me tell y'all a little something
                                Uptown funk you up, uptown funk you up
                                Uptown funk you up, uptown funk you up, uh
                                I said uptown funk you up, uptown funk you up
                                Uptown funk you up, uptown funk you up
                                
                                
                                Come on, dance
                                Jump on it
                                If you sexy then flaunt it
                                If you freaky then own it
                                Don't brag about it, come show me
                                Come on, dance
                                Jump on it
                                If you sexy then flaunt it
                                Well it's Saturday night and we in the spot
                                Don't believe me just watch (come on)
                                
                                
                                Doh
                                Doh doh doh, doh doh doh, doh doh (Hah!)
                                
                                
                                Don't believe me just watch
                                
                                
                                Doh
                                Doh doh doh, doh doh doh, doh doh (Hah!)
                                
                                
                                Don't believe me just watch
                                Don't believe me just watch
                                Don't believe me just watch
                                Don't believe me just watch
                                Hey, hey, hey, oh!
                                
                                
                                Uptown funk you up, uptown funk you up (say whaa?!)
                                Uptown funk you up, uptown funk you up
                                Uptown funk you up, uptown funk you up (say whaa?!)
                                Uptown funk you up, uptown funk you up
                                Uptown funk you up, uptown funk you up (say whaa?!)
                                Uptown funk you up, uptown funk you up
                                Uptown funk you up, uptown funk you up (say whaa?!)
                                Uptown funk you up
                                Aaaaaaow!
                                
                                """,
                        Map.of("artist", "Mark Ronson ft. Bruno Mars", "genre", "Funk/Pop", "year", 2014, "theme", "Fun, dance, confidence")
                ),
                new Document(
                        """
                                First things first
                                I'ma say all the words inside my head
                                I'm fired up and tired of the way that things have been, oh-ooh
                                The way that things have been, oh-ooh
                                Second thing second
                                Don't you tell me what you think that I could be
                                I'm the one at the sail, I'm the master of my sea, oh-ooh
                                The master of my sea, oh-ooh
                                I was broken from a young age
                                Taking my sulking to the masses
                                Writing my poems for the few
                                That look at me, took to me, shook to me, feeling me
                                Singing from heartache from the pain
                                Taking my message from the veins
                                Speaking my lesson from the brain
                                Seeing the beauty through the...
                                Pain!
                                You made me a, you made me a believer, believer
                                Pain!
                                You break me down and build me up, believer, believer
                                Pain!
                                Oh, let the bullets fly, oh, let them rain
                                My life, my love, my drive, it came from...
                                Pain!
                                You made me a, you made me a believer, believer
                                First things first
                                Can you imagine what's about to happen?
                                It's Weezy the Dragon, I link with the Dragons
                                And we gon' get ratchet, no need for imaginin'
                                This is what's happenin'
                                Second thing second, I reckon immaculate
                                Sound about accurate
                                I know that strength, it don't come, don't come without strategy
                                I know the sweet, it don't come without cavities
                                I know the passages come with some traffic
                                I start with from the basement, end up in the attic
                                And third thing third
                                Whoever call me out, they simply can't count
                                Let's get mathematic, I'm up in this, huh
                                Is you a believer?
                                I get a unicorn out of a zebra
                                I wear my uniform like a tuxedo
                                This dragon don't hold his breath, don't need no breather
                                Love you Ms. Cita, the son of a leader
                                I know the bloomin' don't come without rain
                                I know the losin' don't come without shame
                                I know the beauty don't come without hurt
                                Hol' up, hol' up, last thing last
                                I know that Tunechi don't come without Wayne
                                I know that losin' don't come without game
                                I know that glory don't come without...
                                Don't come without...
                                Pain!
                                You made me a, you made me a believer, believer
                                Pain!
                                You break me down and build me up, believer, believer
                                Pain
                                Oh, let the bullets fly, oh, let them rain
                                My life, my love, my drive, it came from...
                                Pain!
                                You made me a, you made me a believer, believer
                                Last things last
                                By the grace of fire and flames
                                You're the face of the future, the blood in my veins, oh-ooh
                                The blood in my veins, oh-ooh
                                But they never did, ever lived, ebbing and flowing
                                Inhibited, limited 'til it broke open and rained down
                                It rained down, like...
                                Pain!
                                You made me a, you made me a believer, believer
                                Pain!
                                You break me down and build me up, believer, believer
                                Pain
                                Oh, let the bullets fly, oh, let them rain
                                My life, my love, my drive, it came from...
                                Pain!
                                You made me a, you made me a believer, believer
                                """,
                        Map.of("artist", "Imagine Dragons", "genre", "Alternative Rock", "year", 2017, "theme", "Struggle to strength")
                ),
                new Document(
                        """
                                The club isn't the best place to find a lover
                                So the bar is where I go (mm)
                                Me and my friends at the table doing shots
                                Drinking fast and then we talk slow (mm)
                                Come over and start up a conversation with just me
                                And trust me I'll give it a chance now (mm)
                                Take my hand, stop, put Van the Man on the jukebox
                                And then we start to dance, and now I'm singing like
                                Girl, you know I want your love
                                Your love was handmade for somebody like me
                                Come on now, follow my lead
                                I may be crazy, don't mind me
                                Say, boy, let's not talk too much
                                Grab on my waist and put that body on me
                                Come on now, follow my lead
                                Come, come on now, follow my lead, mm
                                I'm in love with the shape of you
                                We push and pull like a magnet do
                                Although my heart is falling too
                                I'm in love with your body
                                And last night you were in my room
                                And now my bedsheets smell like you
                                Every day discovering something brand new
                                I'm in love with your body
                                (Oh-I-oh-I-oh-I-oh-I)
                                I'm in love with your body
                                (Oh-I-oh-I-oh-I-oh-I)
                                I'm in love with your body
                                (Oh-I-oh-I-oh-I-oh-I)
                                I'm in love with your body
                                Every day discovering something brand new
                                I'm in love with the shape of you
                                One week in we let the story begin
                                We're going out on our first date (mm)
                                You and me are thrifty, so go all you can eat
                                Fill up your bag and I fill up a plate (mm)
                                We talk for hours and hours about the sweet and the sour
                                And how your family is doing okay (mm)
                                And leave and get in a taxi, then kiss in the backseat
                                Tell the driver make the radio play, and I'm singing like
                                Girl, you know I want your love
                                Your love was handmade for somebody like me
                                Come on now, follow my lead
                                I may be crazy, don't mind me
                                Say, boy, let's not talk too much
                                Grab on my waist and put that body on me
                                Come on now, follow my lead
                                Come, come on now, follow my lead, mm
                                I'm in love with the shape of you
                                We push and pull like a magnet do
                                Although my heart is falling too
                                I'm in love with your body
                                And last night you were in my room
                                And now my bedsheets smell like you
                                Every day discovering something brand new
                                I'm in love with your body
                                (Oh-I-oh-I-oh-I-oh-I)
                                I'm in love with your body
                                (Oh-I-oh-I-oh-I-oh-I)
                                I'm in love with your body
                                (Oh-I-oh-I-oh-I-oh-I)
                                I'm in love with your body
                                Every day discovering something brand new
                                I'm in love with the shape of you
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                Come on, be my baby, come on
                                I'm in love with the shape of you
                                We push and pull like a magnet do
                                Although my heart is falling too
                                I'm in love with your body
                                And last night you were in my room
                                And now my bedsheets smell like you
                                Every day discovering something brand new
                                I'm in love with your body
                                Come on, be my baby, come on
                                Come on (I'm in love with your body), be my baby, come on
                                Come on, be my baby, come on
                                Come on (I'm in love with your body), be my baby, come on
                                Come on, be my baby, come on
                                Come on (I'm in love with your body), be my baby, come on
                                Every day discovering something brand new
                                I'm in love with the shape of you
                                """,
                        Map.of("artist", "Ed Sheeran", "genre", "Pop", "year", 2017, "theme", "Romantic attraction")
                ),
                new Document(
                        """
                                Yeah
                                I've been tryna call
                                I've been on my own for long enough
                                Maybe you can show me how to love, maybe
                                I'm goin' through withdrawals
                                You don't even have to do too much
                                You can turn me on with just a touch, baby
                                I look around and
                                Sin City's cold and empty (oh)
                                No one's around to judge me (oh)
                                I can't see clearly when you're gone
                                I said, "Ooh, I'm blinded by the lights
                                No, I can't sleep until I feel your touch"
                                I said, "Ooh, I'm drowning in the night
                                Oh, when I'm like this, you're the one I trust"
                                (Hey, hey, hey)
                                I'm running out of time
                                'Cause I can see the sun light up the sky
                                So I hit the road in overdrive, baby, oh
                                The city's cold and empty (oh)
                                No one's around to judge me (oh)
                                I can't see clearly when you're gone
                                I said, "Ooh, I'm blinded by the lights
                                No, I can't sleep until I feel your touch"
                                I said, "Ooh, I'm drowning in the night
                                Oh, when I'm like this, you're the one I trust"
                                I'm just walking by to let you know (by to let you know)
                                I could never say it on the phone (say it on the phone)
                                Will never let you go this time (ooh)
                                I said, "Ooh, I'm blinded by the lights
                                No, I can't sleep until I feel your touch"
                                (Hey, hey, hey)
                                (Hey, hey, hey)
                                I said, "Ooh, I'm blinded by the lights
                                No, I can't sleep until I feel your touch"
                                
                                """,
                        Map.of("artist", "The Weeknd", "genre", "Synth-pop", "year", 2019, "theme", "Late-night love and longing")
                ),
                new Document(
                        """
                                I stay out too late
                                Got nothing in my brain
                                That's what people say, mm-mm
                                That's what people say, mm-mm
                                I go on too many dates
                                But I can't make 'em stay
                                At least that's what people say, mm-mm
                                That's what people say, mm-mm
                                But I keep cruisin'
                                Can't stop, won't stop movin'
                                It's like I got this music in my mind
                                Sayin', "It's gonna be alright"
                                'Cause the players gonna play, play, play, play, play
                                And the haters gonna hate, hate, hate, hate, hate
                                Baby, I'm just gonna shake, shake, shake, shake, shake
                                I shake it off, I shake it off (whoo-hoo-hoo)
                                Heartbreakers gonna break, break, break, break, break
                                And the fakers gonna fake, fake, fake, fake, fake
                                Baby, I'm just gonna shake, shake, shake, shake, shake
                                I shake it off, I shake it off (whoo-hoo-hoo)
                                I never miss a beat
                                I'm lightnin' on my feet
                                And that's what they don't see, mm-mm
                                That's what they don't see, mm-mm
                                I'm dancin' on my own (dancin' on my own)
                                I make the moves up as I go (moves up as I go)
                                And that's what they don't know, mm-mm
                                That's what they don't know, mm-mm
                                But I keep cruisin'
                                Can't stop, won't stop groovin'
                                It's like I got this music in my mind
                                Sayin', "It's gonna be alright"
                                'Cause the players gonna play, play, play, play, play
                                And the haters gonna hate, hate, hate, hate, hate
                                Baby, I'm just gonna shake, shake, shake, shake, shake
                                I shake it off, I shake it off (whoo-hoo-hoo)
                                Heartbreakers gonna break, break, break, break, break
                                And the fakers gonna fake, fake, fake, fake, fake
                                Baby, I'm just gonna shake, shake, shake, shake, shake
                                I shake it off, I shake it off (whoo-hoo-hoo)
                                Shake it off, I shake it off
                                I, I, I shake it off, I shake it off
                                I, I, I shake it off, I shake it off
                                I, I, I shake it off, I shake it off (whoo-hoo-hoo)
                                Hey, hey, hey
                                Just think, while you've been gettin' down and out about the liars
                                And the dirty, dirty cheats of the world
                                You could've been gettin' down to this sick beat
                                My ex-man brought his new girlfriend
                                She's like, "Oh my God!" but I'm just gonna shake
                                And to the fella over there with the hella good hair
                                Won't you come on over, baby? We can shake, shake, shake (yeah)
                                Yeah, oh, oh
                                'Cause the players gonna play, play, play, play, play
                                And the haters gonna hate, hate, hate, hate, hate (haters gonna hate)
                                Baby, I'm just gonna shake, shake, shake, shake, shake
                                I shake it off, I shake it off (whoo-hoo-hoo)
                                Heartbreakers gonna break, break, break, break, break (mm)
                                And the fakers gonna fake, fake, fake, fake, fake (and fake and fake and fake)
                                Baby, I'm just gonna shake, shake, shake, shake, shake
                                I shake it off, I shake it off (whoo-hoo-hoo)
                                Shake it off, I shake it off
                                I, I, I shake it off, I shake it off
                                I, I, I shake it off, I shake it off
                                I, I, I shake it off (yeah), I shake it off (whoo-hoo-hoo)
                                Shake it off, I shake it off
                                I, I, I shake it off, I shake it off (you got to)
                                I, I, I shake it off, I shake it off
                                I, I, I shake it off, I shake it off
                                """,
                        Map.of("artist", "Taylor Swift", "genre", "Pop", "year", 2014, "theme", "Letting go of negativity")
                ),
                new Document(
                        """
                                [ Intro ]
                                Look, if you had one shot or one opportunity
                                To seize everything you ever wanted – One moment
                                Would you capture it or just let it slip?
                                [ Verse 1 ]
                                His palms are sweaty, knees weak, arms are heavy
                                There’s vomit on his sweater already, mom’s spaghetti
                                He’s nervous, but on the surface he looks calm and ready
                                To drop bombs, but he keeps on forgettin
                                What he wrote down, the whole crowd goes so loud
                                He opens his mouth, but the words won’t come out
                                He’s chokin, how everybody’s jokin now
                                The clock’s run out, time’s up over, blow
                                Snap back to reality, Oh there goes gravity
                                Oh there goes Rabbit, he choked
                                He’s so mad, but he won’t give up that easy no
                                He won’t have it , he knows his whole back’s to these ropes
                                It don’t matter he’s dope, he knows that
                                But he’s broke, he’s so stacked that he knows
                                When he goes back to his mobile home
                                That’s when it’s back to the lab again yo
                                This whole rap shit
                                He better go capture this moment and hope it don’t pass him
                                [ Chorus ]
                                You better lose yourself in the music, the moment
                                You own it, you better never let it go
                                You only get one shot, do not miss your chance to blow
                                This opportunity comes once in a lifetime yo
                                [ Verse 2 ]
                                The soul’s escaping, through this hole that it’s gaping
                                This world is mine for the taking
                                Make me king, as we move toward a new world order
                                A normal life is borin
                                But superstardom’s close to post mortem
                                It only grows harder, only grows hotter
                                He blows us all over these hoes is all on him
                                Coast to coast shows, he’s know as the globetrotter
                                Lonely roads, God only knows
                                He’s grown farther from home, he’s no father
                                He goes home and barely knows his own daughter
                                But hold your nose cause here goes the cold water
                                His hoes don’t want him no mo, he’s cold product
                                They moved on to the next schmoe who flows
                                He nose dove and sold nada
                                So the soap opera is told and unfolds
                                I suppose it’s old partna’
                                But the beat goes on Da da dum da dum da da
                                [ Chorus ]
                                [ Verse 3 ]
                                No more games I’ma change what you call rage
                                Tear this mothafuckin roof off like 2 dogs caged
                                I was playin in the beginnin the mood all changed
                                I been chewed up and spit out and booed off stage
                                But I kept rhymin and stepwritin the next cypher
                                Best believe somebody’s payin the pied piper
                                All the pain inside amplified by the fact
                                That I can’t get by with my 9 to 5
                                And I can’t provide the right type of life for my family
                                Cause man these goddam food stamps don’t buy diapers
                                And it’s no movie there’s no Mekhi Phifer, this is my life
                                And these times are so hard and it’s getting even harder
                                Tryin to feed and water my seed, plus
                                See dishonor caught up between being a father and a prima donna
                                Baby mama drama’s screamin on and too much for me to wanna
                                Stay in one spot, another day of monotony
                                Has gotten me to the point, I’m like a snail
                                I’ve got to formulate a plot or I’ll end up in jail or shot
                                Success is my only mothafuckin option, failure’s not
                                Mom I love you, but this trailer’s got to go
                                I cannot grow old in Salem’s lot so here I go its my shot
                                Feet fail me not cuz maybe the only opportunity that I got
                                [ Chorus ]
                                [ Outro ]
                                You can do anything you set your mind to man …
                                
                                """,
                        Map.of("artist", "Eminem", "genre", "Hip-Hop", "year", 2002, "theme", "Seizing opportunity")
                ),
                new Document(
                        """
                                I found a love for me
                                Oh, darling, just dive right in and follow my lead
                                Well, I found a girl, beautiful and sweet
                                Oh, I never knew you were the someone waitin' for me
                                'Cause we were just kids when we fell in love, not knowin' what it was
                                I will not give you up this time
                                Oh, darling, just kiss me slow, your heart is all I own
                                And in your eyes, you're holding mine
                                Baby, I'm dancin' in the dark with you between my arms
                                Barefoot on the grass while listenin' to our favourite song
                                When you said you looked a mess, I whispered underneath my breath
                                But you heard it, "Darling, you look perfect tonight"
                                Well, I found a woman, stronger than anyone I know
                                She shares my dreams, I hope that someday, I'll share her home
                                I found a love to carry more than just my secrets
                                To carry love, to carry children of our own
                                We are still kids, but we're so in love, fightin' against all odds
                                I know we'll be alright this time
                                Darling, just hold my hand, be my girl, I'll be your man
                                I see my future in your eyes
                                Oh, baby, I'm dancin' in the dark with you between my arms
                                Barefoot on the grass while listenin' to our favourite song
                                When I saw you in that dress, lookin' so beautiful
                                I don't deserve this, darling, you look perfect tonight
                                No, no, no
                                Mm, oh
                                Baby, I'm dancin' in the dark with you between my arms
                                Barefoot on the grass while listenin' to our favourite song
                                I have faith in what I see, now I know I have met
                                An angel in person, and she looks perfect
                                Though I don't deserve this, you look perfect tonight
                                
                                """,
                        Map.of("artist", "Ed Sheeran", "genre", "Pop", "year", 2017, "theme", "Romantic devotion")
                ),
                new Document(
                        """
                                Lately, I been, I been losin′ sleep
                                Dreamin' about the things that we could be
                                But baby, I been, I been prayin′ hard
                                Said, no more countin' dollars, we'll be countin′ stars
                                Yeah, we′ll be countin' stars
                                verse
                                I see this life like a swingin′ vine
                                Swing my heart across the line
                                In my face is flashin' signs
                                Seek it out and ye shall find
                                hook
                                Old, but I′m not that old
                                Young, but I'm not that bold
                                And I don′t think the world is sold
                                On just doin' what we're told
                                pre-chorus
                                I feel somethin′ so right doin′ the wrong thing
                                And I feel somethin' so wrong doin′ the right thing
                                I couldn't lie, couldn′t lie, couldn't lie
                                Everything that kills me makes me feel alive
                                chorus
                                Lately, I been, I been losin′ sleep (hey!)
                                Dreamin' about the things that we could be
                                But baby, I been, I been prayin' hard (hey!)
                                Said, no more countin′ dollars, we′ll be countin' stars
                                Lately, I been, I been losin′ sleep (hey!)
                                Dreamin' about the things that we could be
                                But baby, I been, I been prayin′ hard
                                Said, no more countin' dollars, we′ll be, we'll be countin' stars, yeah
                                verse
                                I feel your love, and I feel it burn
                                Down this river, every turn
                                "Hope" is our four-letter word
                                Make that money, watch it burn
                                hook
                                Old, but I′m not that old
                                Young, but I′m not that bold
                                And I don't think the world is sold
                                On just doin′ what we're told
                                pre-chorus
                                And I feel somethin′ so wrong doin' the right thing
                                I couldn′t lie, couldn't lie, couldn't lie
                                Everything that drowns me makes me wanna fly
                                chorus
                                Lately, I been, I been losin′ sleep (hey!)
                                Dreamin′ about the things that we could be
                                Baby, I been, I been prayin' hard (hey!)
                                Said, no more countin′ dollars, we'll be countin′ stars (ooh)
                                Lately, I been, I been losin' sleep (ooh, ooh, hey)
                                Dreamin′ about the things that we could be (ooh, ooh)
                                But baby, I been, I been prayin' hard (ooh, ooh)
                                Said, no more countin' dollars, we′ll be, we′ll be countin' stars (ooh, ooh)
                                bridge
                                Oh, take that money, watch it burn
                                Sink in the river the lessons I learned
                                Take that money, watch it burn
                                Sink in the river the lessons I learned
                                Take that money, watch it burn
                                Sink in the river the lessons I learned
                                Take that money, watch it burn
                                Sink in the river the lessons I learned
                                pre-chorus
                                Everything that kills me makes me feel alive
                                chorus
                                Lately, I been, I been losin′ sleep (hey!)
                                Dreamin' about the things that we could be
                                Baby, I been, I been prayin′ hard (hey!)
                                Said, no more countin' dollars, we′ll be countin' stars (ooh)
                                Lately, I been, I been losin' sleep (ooh, ooh hey!)
                                Dreamin′ about the things that we could be (ooh, ooh)
                                But baby, I been, I been prayin′ hard (ooh, ooh)
                                Said, no more countin' dollars, we′ll be, we'll be countin′ stars (ooh, ooh)
                                outro
                                Take that money, watch it burn (ooh)
                                Sink in the river, the lessons I learned (ooh)
                                Take that money, watch it burn (ooh)
                                Sink in the river, the lessons I learned (ooh)
                                Take that money, watch it burn (ooh)
                                Sink in the river, the lessons I learned (ooh)
                                Take that money, watch it burn (ooh)
                                Sink in the river, the lessons I learned
                                
                                """,
                        Map.of("artist", "OneRepublic", "genre", "Pop Rock", "year", 2013, "theme", "Dreams vs reality")
                )
        );

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(500)
                .build();

        List<Document> chunks = splitter.apply(songsList);
        vectorStore.add(chunks);
    }

}
