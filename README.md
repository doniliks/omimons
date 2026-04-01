# Omimon Battle Simulator

Java-based battle simulator inspired by Pokémon, developed for the **Object-Oriented Modeling and Implementation** course at the University of Klagenfurt.

## Overview

The system simulates battles between creatures called **Omimon** that are trained by trainers.  
Trainers can capture, train, and battle Omimon against wild creatures or other trainers.

## Features

- Central **Omidex** containing base Omimon data (Singleton)
- Trainers with teams of up to 6 Omimon
- Turn-based **battle system**
- Attacks with **modular effects** (e.g. healing, multi-hit, critical hit)
- **Type system** influencing damage (Fire, Water, Plant, Normal)
- Strategy-based decision making for trainers

## Design Concepts

The project focuses on object-oriented modeling and design patterns:

- Object-Oriented Programming (OOP)
- UML class diagram design
- **Strategy pattern** for trainer decisions
- **Decorator pattern** for attack effects
- **Singleton pattern** for the Omidex

## Technologies

- Java
- UML
- Design Patterns

## Running the Project

The project is demonstrated through a **main method** that simulates battles between trainers and Omimon.
