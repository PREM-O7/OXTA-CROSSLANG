#!/bin/bash
echo "Setting up OXTA CrossLang..."
g++ core/ai_engine.cpp -o core/ai_engine
javac backend/Server.java
javac ui/CodeAssistantGUI.java
echo "Setup Complete!"
