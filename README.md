
a. Name of the project, your name, UID, and email.

Assignment 4: Room Database and Fragments
Jon England
u0978321
u0978321@umail.utah.edu

b. A brief description of what this app does and any known bugs.

This app allows a user to construct and keep track of Journal entries. This is accomplished by clicking the "+" button
and then entering the: title, date, start-time, end-time. After this, the user must save the entry by hitting the save button.
The entry is then saved on the database and displayed on the main list screen. You may also click the entry and have the option to delete or share it.
Deleting the entry will remove it from the database and from the entry-list screen. Sharing will pop a dialog with share options and
allow the user to select where they would like to share it. After they select an option and click ok or cancel, nothing will happen
(as per the instructions). If the entry is blank the user will not be able to share it (I added this functionality).

Bugs that are included and not mentioned in the instructions are:

- you may create a blank entry and leave out any detail of the entry. when doing this, it may display a blank entry in the list.

c. A brief description of how you completed the tasks (1-4) above. If you did
something different from what was in the videos when completing the task (1),
include a line or two; otherwise, say “no major changes.”
● If you did not complete any of the tasks, indicate so.
● Do not repeat the code changes, but include enough details so the TA will get
an idea of what changes you have made by just reading the plain text.
● If you borrowed ideas from someone or somewhere (including from the
web), attribute properly.

NOTE: I poked around the web to compete most of the tasks, but only linked references to code/codechunks that took code from

Task 1:
No major changes

Task 2:
I completed task 2 by doing a multitude of things to the EntryDetailsViewModel, Journal Entry class, and their associated xml files.
I first started by creating new strings for labels and text used by the xml files. I then restructured the journal_entry_detail xml file
to include start-time, end-time, and Date. After this, I found all files that included duration and replaced all these with the variables
for date, start-time, and end-time. I went into the Journal Entry class and added fields mentioned above with their appropriate getters/setters.
Next I linked id's to the buttons and added OnClick methods for each variable in EntryDetailsFragment. Once this was done, I found the documentation
for Timepickerdialog and Datepickerdialog and implemented the functionality into the button press listeners for each variable. After I accomplished this,
I made my way to the EntryListFragment to not only update its xml layout, but add functionality to display the date, start-time, and end-time to each list
entry.

Task 3:
I completed this delete functionality by adding a xml file to the menu resources that matched the name of the detail xml file in the layout.
In here, I added a menu option named delete. With this, I defined 2 methods in the details class that (1) displayed the menu item and (2) Defined what
that menu item did when clicked. To define this I needed an alertDialog box that did something when "yes" was selected. For this I had to modify
the JournalEntryDao to include a database operation "delete" Now that this was done I could call entry.delete() back in the menu options method.
This deleted the entry and returned me back to the main list activity screen.

Task 4:
I completed this task in a very similar fashion to the previous task, in that, i had to add another menu option in the xml file and set
up the functionality for when it was pressed. To do this I used another alertDialog that this time included singleChoiceItems defined to be
only text and email. I had to use a customtitle because the title was getting cut off and you cannot have a dialog message AND dialog choice items.
After everything, I decided to add one extra thing: a different dialog box pops up if the user hasn't saved the entry. This was to avoid the fact
that the user could share an empty entry; which makes no sense. This was accomplished by checking to see if all fields were filled in before allowing
the user to share.


d. We saw some enhancements to the basic JournalApp in this assignment. Suggest 2-
3 ideas to further enhance the app. The enhancements could be in terms of UI/UX,
back end, or functionality. (You don’t have to implement those suggestions, so go
nuts!)

1. Customize the app to allow for image file upload, so the user could attach an image of the activity they describe in their entry
2. Add a feature for deleting all entry items.
3. Add a description feature that gave a more detailed description about the entry.

e. Estimated number of hours it took you to complete this assignment.

This assignment took me roughly 10-12 hours to complete.

f. Rate this assignment in terms of difficulty, on a scale of 1 to 10, 10 being the most
difficult.
I would rate the difficulty between 7 and 8

References:
- Lecture videos
- https://abhiandroid.com/ui/alertdialog
- https://www.youtube.com/watch?v=yTGjJi48KSQ
- https://stackoverflow.com/questions/28643277/dialog-box-title-text-size-in-android