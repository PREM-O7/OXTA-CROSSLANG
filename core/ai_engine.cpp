#include <iostream>
#include <string>
#include <fstream>

std::string suggestCode(const std::string &input) {
    if (input == "for") {
        return "for(int i = 0; i < n; i++) { }";
    } else if (input == "if") {
        return "if(condition) { }";
    } else {
        return "No suggestions available.";
    }
}

int main() {
    std::string userInput;
    std::cout << "Enter code snippet: ";
    std::cin >> userInput;
    
    std::string suggestion = suggestCode(userInput);
    
    std::ofstream outfile("suggestion.txt");
    outfile << suggestion;
    outfile.close();
    
    return 0;
}
