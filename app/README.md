
a. Name of the project, your name, UID, and email.

Assignment 3: Persisting the UI state, Jon England, u0978321, u0978321@utah.edu

b. A brief description of what this app does, and any known bugs.

This app allows the user to roll a six sided die on the press of the button.
Stats about previous rolls, double rolls, and overall total rolls are tracked and displayed for the user.
There are 2 instances where you may increase the coin balance displayed: (1) you may roll a 6 and (2)
you roll two 6's in a row. You only lose coins if you roll any number but a 6 twice in a row.

c. A brief description of how you completed the tasks (1-4) above. If you did
something different from what was in the videos when completing the task (1),
include a line or two; otherwise, say “no major changes.”
● If you did not complete any of the tasks, indicate so.
● Do not repeat the code changes, but include enough details so the TA will get
an idea of what changes you have made by just reading the plain text.
● If you borrowed ideas from someone or somewhere (including from the
web), attribute properly.

When it came to completing the tasks, There were "no major changes" for task 1. For task 2 this was easily accomplished by:
following the link on toasty, then copying and pasting the first code section, and ,finally, changing the text displayed. Task 3
was accomplished by modifying the layout xml by including another linear layout and nesting 2 labels with their corresponding values inside of it.
Task 4 was very similar to task 3. I created 2 more linear layouts and appropriately nested the labels and their values inside of them. I then had to add some
logic inside the java classes to account for a double 6 and a double (anything but 6). I also changed some of the text and primary colors for style.


d. We saw some enhancements to the basic WalletActivity in this assignment. Suggest
2-3 ideas to further enhance the app even at this one-activity stage. The
enhancements could be in terms of UI/UX or functionality. (You don’t have to
implement those suggestions, so go nuts!)

Some of the changes could be:
1. adding a allowedRolls variable that limited the number of rolls the user may roll and keep "coin amount" highscores to reflect this change.
2. adding a small probabilty chart at the top or bottom of the screen that gave the user an idea of the likelyhoods/percentage chance of getting,
 for instance, two consecutive six rolls. Do this for every possible likelyhood.
3. adding a sound indication for specific rolls. A regular roll of say, 2, would play a normal low-pitched beeping noice. However,
  if you rolled a double 6, it would make a high-pitched achievement-unlocking sound. This would create a more engaging user experience
  by incorporating stimulation to another one of the five senses.

e. Estimated number of hours it took you to complete this assignment.
 This assignment took around 4-5 hours
f. Rate this assignment in terms of difficulty, on a scale of 1 to 10, 10 being the most
difficult.
 I would give this assignment a 3/10 on the difficulty scale.