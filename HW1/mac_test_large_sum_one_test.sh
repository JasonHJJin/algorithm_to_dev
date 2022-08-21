#!/bin/bash

file=$(find -E . -iregex ".*largesum.py|.*largesum.java|.*largesum.c|.*largesum.cpp")
file=${file:2} # Strip away leading ./

if [ ! -f "$file" ]; then
    echo -e "Error: File '$file' not found.\nTest failed."
    exit 1
fi

num_right=0
total=0
line="________________________________________________________________________"
compiler=
interpreter=
language=
extension=${file##*.}
if [ "$extension" = "py" ]; then
    if [ ! -z "$PYTHON_PATH" ]; then
        interpreter=$(which python.exe)
    else
        interpreter=$(which python3)
    fi
    command="$interpreter $file"
    echo -e "Testing $file\n"
elif [ "$extension" = "java" ]; then
    language="java"
    command="java ${file%.java}"
    echo -n "Compiling $file..."
    javac $file
    echo -e "done\n"
elif [ "$extension" = "c" ] || [ "$extension" = "cpp" ]; then
    language="c"
    command="./${file%.*}"
    echo -n "Compiling $file..."
    results=$(make 2>&1)
    if [ $? -ne 0 ]; then
        echo -e "\n$results"
        exit 1
    fi
    echo -e "done\n"
fi

totaltime=0
run_test() {
    (( ++total ))
    echo -n "Running test $total..."
    expected=$1
    command="$interpreter $file $input"
    start_time=$(gdate +%s.%6N)
    received=$( $command 2>&1 | tr -d '\r' )
    end_time=$(gdate +%s.%6N)
    elapsed=$(echo "scale=3; $end_time - $start_time" | bc)
    totaltime=$(echo "$totaltime + $elapsed" | bc)
    echo -e "\nTime Elapsed: $elapsed"
    if [ "$expected" = "$received" ]; then
        echo -e "success\n"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

input="input1.txt"
run_test "$(<./output1.txt)"

input="input2.txt"
run_test "$(<./output2.txt)"

input="input3.txt"
run_test "$(<./output3.txt)"

input="input4.txt"
run_test "$(<./output4.txt)"

input="input5.txt"
run_test "$(<./output5.txt)"

input="input6.txt"
run_test "$(<./output6.txt)"

input="input7.txt"
run_test "$(<./output7.txt)"

input="input8.txt"
run_test "$(<./output8.txt)"

input="input9.txt"
run_test "$(<./output9.txt)"

input="input10.txt"
run_test "$(<./output10.txt)"

avetime=$(echo "scale=6; $totaltime / $total" | bc)
echo -e "Average Runtime: $avetime"
echo -e "Total tests run: $total"
echo -e "Number correct : $num_right"
echo -n "Percent correct: "
echo "scale=2; 100 * $num_right / $total" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
elif [ "$language" = "c" ]; then
    echo -e -n "\nCleaning project..."
    make clean > /dev/null 2>&1
    echo "done"
fi
