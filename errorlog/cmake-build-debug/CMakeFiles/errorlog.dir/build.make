# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/clion/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /opt/clion/bin/cmake/linux/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/nadav/IdeaProjects/2019-Spinupavm/errorlog

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/errorlog.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/errorlog.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/errorlog.dir/flags.make

CMakeFiles/errorlog.dir/main.c.o: CMakeFiles/errorlog.dir/flags.make
CMakeFiles/errorlog.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/errorlog.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/errorlog.dir/main.c.o   -c /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/main.c

CMakeFiles/errorlog.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/errorlog.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/main.c > CMakeFiles/errorlog.dir/main.c.i

CMakeFiles/errorlog.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/errorlog.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/main.c -o CMakeFiles/errorlog.dir/main.c.s

# Object files for target errorlog
errorlog_OBJECTS = \
"CMakeFiles/errorlog.dir/main.c.o"

# External object files for target errorlog
errorlog_EXTERNAL_OBJECTS =

errorlog: CMakeFiles/errorlog.dir/main.c.o
errorlog: CMakeFiles/errorlog.dir/build.make
errorlog: CMakeFiles/errorlog.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable errorlog"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/errorlog.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/errorlog.dir/build: errorlog

.PHONY : CMakeFiles/errorlog.dir/build

CMakeFiles/errorlog.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/errorlog.dir/cmake_clean.cmake
.PHONY : CMakeFiles/errorlog.dir/clean

CMakeFiles/errorlog.dir/depend:
	cd /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/nadav/IdeaProjects/2019-Spinupavm/errorlog /home/nadav/IdeaProjects/2019-Spinupavm/errorlog /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug /home/nadav/IdeaProjects/2019-Spinupavm/errorlog/cmake-build-debug/CMakeFiles/errorlog.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/errorlog.dir/depend

