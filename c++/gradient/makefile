CC = g++
CPPFLAGS = -O3

TARGET = gradient
DEBUG_TARGET = $(TARGET)d
BENCHMARK_TARGET = $(TARGET)_benchmark

$(TARGET): $(TARGET).cpp
	$(CC) $(CPPFLAGS) -o $(TARGET) $(TARGET).cpp

debug: $(TARGET).cpp
	$(CC) $(CPPFLAGS) -DDEBUG -o $(DEBUG_TARGET) $(TARGET).cpp

benchmark: $(TARGET).cpp
	$(CC) $(CPPFLAGS) -DNUM_REPETITIONS=10 -o $(BENCHMARK_TARGET) $(TARGET).cpp
	
clean:
	$(RM) $(TARGET) *.o *~ *.swp *.out $(DEBUG_TARGET) $(BENCHMARK_TARGET)
