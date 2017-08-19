[![Build Status](https://travis-ci.org/msigwart/fakeload.svg?branch=master)](https://travis-ci.org/msigwart/fakeload)

# FakeLoad
FakeLoad is an open-source Java library which provides a simple way of producing “fake" system loads in applications or tests.

Producing "FakeLoads" is as simple as this:
```java
// Creation
FakeLoad fakeload = FakeLoads.create()
 .lasting(10, TimeUnit.SECONDS)
 .withCpu(80)
	.withMemory(300, MemoryUnit.MB);
 
// Execution
FakeLoadExecutor executor = FakeLoadExecutors.newDefaultExecutor(); 
executor.execute(fakeload);
```
## Motivation
## Getting Started
### Installation

### Usage
In development and testing there is sometimes a need to to put the software system under stress to test certain aspects of the system. For example, developers might want to test the rescaling behaviour of their application by simulating high CPU or memory load, test the precision of their monitoring infrastructure or just simply “simulate” some complex operation.
 
The simulated load should be controllable and exact in a way that allows precise analysis of the system’s behaviour under stress. Therefore developers often find themselves writing specific “simulation code” instead of focusing on what’s best for their application.
 
This is where FakeLoad comes in. FakeLoad is an easy to use library which which enables you to create specific simulation loads within your application where ever and whenever needed.
