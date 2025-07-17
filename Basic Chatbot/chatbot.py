
#import everything from file text.py
from text import *

import random

# Function to generate a response
def getResponse(userInput, i):
    userInput = userInput.lower() #sets the user input to all lowercase
    #if the input is any of these then the answer is random
    if userInput == "tell me a joke" or userInput == "whats a joke" or userInput == "what is a joke" or userInput == "do you know a joke":
        response = responses.get(userInput, responses["default"]) #checks the database for input
        return random.choice(response) #returns random answer from input
    #checks the database for input, if input is not there then returns default saying it doesn't understand
    response = responses.get(userInput, responses["default"])
    return(response)[i] #returns to main loop

# Main loop
print("Chatbot: Hi! How can I help you?")
temporary = " " #variable to hold previous input
while True: #until loop breaks
    i = 0
    userInput = input("You: ") #user input
    #if the user input is any of the below then the chatbot gives a different answer to the previous input
    if userInput.lower() == "more" or userInput.lower() == "expand" or userInput.lower() == "tell me more" or userInput.lower() == "expand on this":
        i = i + 1
        print("Chatbot:", getResponse(temporary, i)) #passes in the previous input
        i = 0
    else:
        if userInput.lower() == "exit" or userInput.lower() == "quit": #ends the program if either of these are input
            print("Chatbot: Goodbye!")
            break
        else:
            print("Chatbot:", getResponse(userInput, i))#passes the input to the getResponse function
            temporary = userInput #sets the input to temporary in case the user asks for more detail
