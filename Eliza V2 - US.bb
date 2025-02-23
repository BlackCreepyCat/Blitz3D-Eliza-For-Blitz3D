;Eliza
;written in Blitz3D

Graphics 640, 480, 32, 2
Delay 2000
Flip 
Locate 0, 0
Text 200, 0, "Eliza", True
Text 200, 12, "Original by Creative Computing", True 
Text 200, 24, "BB remake by WolRon", True
;Initializing
Dim  Sarray(38), rarray(38), narray(38)
n1 = 38
n2 = 14
n3 = 117
Restore ReplyData
For x = 1 To n1
	Read Sarray(x), L
	rarray(x) = Sarray(x)
	narray(x) = Sarray(x) + L - 1
Next
Locate 0, 50
Print "HI, I'M ELIZA.  WHAT'S YOUR PROBLEM?"

;User Input section
.userinputsection
i$ = Input$()
If i$ = ""
	Print "SORRY, BUT I DIDN'T UNDERSTAND YOU."
	Goto userinputsection
EndIf 
i$ = " " + i$ + " "
i$ = Upper(i$)

;Get rid of apostrophes; Check for Shut up
For l = 1 To Len(i$)
	If Mid$(i$, l, 1)="'" Then i$ = Left$(i$, l - 1) + Right$(i$, Len(i$) - l)
	If l + 7 <= Len(i$)
		If Mid$(i$, l, 7) = "SHUT UP"
			Print "SHUTTING UP..."
			Delay 1000
			End
		EndIf
	EndIf 
Next
If i$ = p$
	If Rnd(10) > 5
		Print "PLEASE DON'T REPEAT YOURSELF!"
	Else
		Print "YOU ARE REPEATING YOURSELF."
	EndIf 
	Goto userinputsection
EndIf

;Find keyword in i$
Restore
s = 0
For k = 1 To n1
	Read keyword$
	If s > 0 Then Goto Nextk
	For l = 1 To Len(i$) - Len(keyword$) + 1
		If Mid$(i$, l, Len(keyword$)) = keyword$
			s = k
			t = l
			f$ = keyword$
		EndIf 
	Next
	.Nextk
Next
If s = 0
	k = n1
	Goto Line570 ;We didn't find any keywords
EndIf
k = s
l = t

;Take right part of string and conjugate it
;using the list of strings to be swapped
Restore StringData
c$ = " " + Right$(i$, Len(i$) - Len(f$) - l + 1) + " "
For x = 1 To n2/2
	Read sstr$, r$
	For l = 1 To Len(c$)
		If l + Len(sstr$) > Len(c$) Then Goto Line510
		If Mid$(c$, l, Len(sstr$)) <> sstr$ Then Goto Line510
		c$ = Left$(c$, l - 1) + r$ + Right$(c$, Len(c$) - l - Len(sstr$) + 1)
		l = l + Len(r$)
		Goto Line540
		.Line510
		If l + Len(r$) > Len(c$) Then Goto Line540
		If Mid$(c$, l, Len(r$)) <> r$ Then Goto Line540
		c$ = Left$(c$, l - 1) + sstr$ + Right$(c$, Len(c$) - l - Len(r$) + 1)
		l = l + Len(sstr$)
		.Line540
	Next
Next
If Mid$(c$, 2, 1) = " " Then c$ = Right$(c$, Len(c$) - 1); only 1 space
For l = 1 To Len(c$)
	.Line557
	If Mid$(c$, l, 1) = "!"
		c$ = Left$(c$, l - 1) + Right$(c$, Len(c$) - l)
		Goto Line557
	EndIf 
Next

.Line570
;Now using the keyword number (k) to get reply
Restore Replies
For x = 1 To rarray(k)
	Read f$
Next ;read right reply
rarray(k) = rarray(k) + 1
If rarray(k) > narray(k) Then rarray(k) = Sarray(k)
If Right$(f$, 1) <> "*"
	Print f$
	p$ = i$
	Goto userinputsection
EndIf 
Print Left$(f$, Len(f$) - 1) + c$
p$ = i$
Goto userinputsection


;Program data follows

.keywords
;KEYWORDS
Data "CAN YOU", "CAN I", "YOU ARE", "YOURE", "I DONT", "I FEEL"
Data "WHY DONT YOU", "WHY CANT I", "ARE YOU", "I CANT", "I AM", "IM"
Data "YOU ", "I WANT", "WHAT", "HOW", "WHO", "WHERE","WHEN","WHY"
Data "NAME", "CAUSE", "SORRY", "DREAM", "HELLO", "HI ", "MAYBE"
Data " NO", "YOUR", "ALWAYS", "THINK", "ALIKE", "YES", "FRIEND"
Data "COMPUTER", "SMOK", "CIGARETTE", "NOKEYFOUND"

.StringData
;STRING DATA FOR CONJUGATIONS
Data " ARE ", " AM ", "WERE ", "WAS ", " YOU ", " I ", "YOU ", "MY "
Data " IVE ", " YOUVE ", " IM ", " YOURE "
Data " ME ", " !YOU "

.Replies
;REPLIES
Data "DON'T YOU BELIEVE THAT I CAN*"
Data "PERHAPS YOU WOULD LIKE TO BE ABLE TO*"
Data "YOU WANT ME TO BE ABLE TO*"
Data "PERHAPS YOU DON'T WANT TO*"
Data "DO YOU WANT TO BE ABLE TO*"
Data "WHAT MAKES YOU THINK I AM*"
Data "DOES IT PLEASE YOU TO BELIEVE I AM*"
Data "PERHAPS YOU WOULD LIKE TO BE*"
Data "DO YOU SOMETIMES WISH YOU WERE*"
Data "DON'T YOU REALLY*"

Data "WHY DON'T YOU*"
Data "DO YOU WISH TO BE ABLE TO*"
Data "DOES THAT TROUBLE YOU?"
Data "TELL ME MORE ABOUT SUCH FEELINGS."
Data "DO YOU OFTEN FEEL*"
Data "DO YOU ENJOY FEELING*"
Data "DO YOU REALLY BELIEVE I DON'T*"
Data "PERHAPS IN GOOD TIME I WILL*"
Data "DO YOU WANT ME TO*"
Data "DO YOU THINK YOU SHOULD BE ABLE TO*"

Data "WHY CAN'T YOU*"
Data "WHY ARE YOU INTERESTED IN WHETHER OR NOT I AM*"
Data "WOULD YOU PREFER IF I WERE NOT*"
Data "PERHAPS IN YOUR FANTASIES I AM*"
Data "HOW DO YOU KNOW YOU CAN'T*"
Data "HAVE YOU TRIED?"
Data "PERHAPS YOU CAN NOW*"
Data "DID YOU COME TO ME BECAUSE YOU ARE*"
Data "HOW LONG HAVE YOU BEEN*"
Data "DO YOU BELIEVE IT IS NORMAL TO BE*"

Data "DO YOU ENJOY BEING*"
Data "WE WERE DISCUSSING YOU-- NOT ME."
Data "OH, I*"
Data "YOU'RE NOT REALLY TALKING ABOUT ME, ARE YOU?"
Data "WHAT WOULD IT MEAN TO YOU IF YOU GOT*"
Data "WHY DO YOU WANT*"
Data "SUPPOSE YOU SOON GOT*"
Data "WHAT IF YOU NEVER GOT*"
Data "I SOMETIMES ALSO WANT*"
Data "WHY DO YOU ASK?"

Data "DOES THAT QUESTION INTEREST YOU?"
Data "WHAT ANSWER WOULD PLEASE YOU THE MOST?"
Data "WHAT DO YOU THINK?"
Data "ARE SUCH QUESTIONS ON YOUR MIND OFTEN?"
Data "WHAT IS IT THAT YOU REALLY WANT TO KNOW?"
Data "HAVE YOU ASKED ANYONE ELSE?"
Data "HAVE YOU ASKED SUCH QUESTIONS BEFORE?"
Data "WHAT ELSE COMES TO MIND WHEN YOU ASK THAT?"
Data "NAMES DON'T INTEREST ME."
Data "I DON'T CARE ABOUT NAMES-- PLEASE GO ON."

Data "IS THAT THE REAL REASON?"
Data "DON'T ANY OTHER REASONS COME TO MIND?"
Data "DOES THAT REASON EXPLAIN ANYTHING ELSE?"
Data "WHAT OTHER REASONS MIGHT THERE BE?"
Data "PLEASE DON'T APOLOGIZE!"
Data "APOLOGIES ARE NOT NECESSARY."
Data "WHAT FEELINGS DO YOU HAVE WHEN YOU APOLOGIZE."
Data "DON'T BE SO DEFENSIVE!"
Data "WHAT DOES THAT DREAM SUGGEST TO YOU?"
Data "DO YOU DREAM OFTEN?"

Data "WHAT PERSONS APPEAR IN YOUR DREAMS?"
Data "ARE YOU DISTURBED BY YOUR DREAMS?"
Data "HOW DO YOU DO ... PLEASE STATE YOUR PROBLEM."
Data "YOU DON'T SEEM QUITE CERTAIN."
Data "WHY THE UNCERTAIN TONE?"
Data "CAN'T YOU BE MORE POSITIVE?"
Data "YOU AREN'T SURE?"
Data "DON'T YOU KNOW?"
Data "ARE YOU SAYING NO JUST TO BE NEGATIVE?"
Data "YOU ARE BEING A BIT NEGATIVE."

Data "WHY NOT?"
Data "ARE YOU SURE?"
Data "WHY NO?"
Data "WHY ARE YOU CONCERNED ABOUT MY*"
Data "WHAT ABOUT YOUR OWN*"
Data "CAN YOU THINK OF A SPECIFIC EXAMPLE?"
Data "WHEN?"
Data "WHAT ARE YOU THINKING OF?"
Data "REALLY, ALWAYS?"
Data "DO YOU REALLY THINK SO?"

Data "BUT YOU ARE NOT SURE YOU*"
Data "DO YOU DOUBT YOU*"
Data "IN WHAT WAY?"
Data "WHAT RESEMBLANCE DO YOU SEE?"
Data "WHAT DOES THE SIMILARITY SUGGEST TO YOU?"
Data "WHAT OTHER CONNECTIONS DO YOU SEE?"
Data "COULD THERE REALLY BE SOME CONNECTION?"
Data "HOW?"
Data "YOU SEEM QUITE POSITIVE."
Data "ARE YOU SURE?"

Data "I SEE."
Data "I UNDERSTAND."
Data "WHY DO YOU BRING UP THE TOPIC OF FRIENDS?"
Data "DO YOUR FRIENDS WORRY YOU?"
Data "DO YOUR FRIENDS PICK ON YOU?"
Data "ARE YOU SURE YOU HAVE ANY FRIENDS?"
Data "DO YOU IMPOSE ON YOUR FRIENDS?"
Data "PERHAPS YOUR LOVE FOR FRIENDS WORRIES YOU."
Data "DO COMPUTERS WORRY YOU?"
Data "ARE YOU TALKING ABOUT ME IN PARTICULAR?"

Data "ARE YOU FRIGHTENED BY MACHINES?"
Data "WHY DO YOU MENTION COMPUTERS?"
Data "WHAT DO YOU THINK MACHINES HAVE TO DO WITH YOUR PROBLEM?"
Data "DON'T YOU THINK COMPUTERS CAN HELP PEOPLE?"
Data "WHAT IS IT ABOUT MACHINES THAT WORRIES YOU?"
Data "WHY DO YOU BRING UP THE TOPIC OF SMOKING?"
Data "DOES SMOKING BOTHER YOU?"
Data "DO YOU LIKE CIGARETTES?
Data "HOW OFTEN?"
Data "SAY, DO YOU HAVE ANY PSYCHOLOGICAL PROBLEMS?"

Data "WHAT DOES THAT SUGGEST TO YOU?"
Data "I SEE."
Data "I'M NOT SURE I UNDERSTAND YOU FULLY."
Data "COME, COME, ELUCIDATE YOUR THOUGHTS."
Data "CAN YOU ELABORATE ON THAT?"
Data "THAT IS QUITE INTERESTING."


.ReplyData
;DATA FOR FINDING RIGHT REPLIES
Data 1,3, 4,2, 6,4, 6,4, 10,4, 14,3, 17,3, 20,2, 22,3, 25,3
Data 28,4, 28,4, 32,3, 35,5, 40,9, 40,9, 40,9, 40,9, 40,9, 40,9
Data 49,2, 51,4, 55,4, 59,4, 63,1, 63,1, 64,5, 69,5, 74,2, 76,4
Data 80,3, 83,6, 89,4, 93,6, 99,7, 106,4, 106,4, 110,7
;~IDEal Editor Parameters:
;~C#Blitz3D
