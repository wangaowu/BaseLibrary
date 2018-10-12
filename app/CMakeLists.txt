cmake_minimum_required(VERSION 3.4.1)

add_library(   cpp-lib  SHARED   src/main/cpp/cpp-lib.cpp )

find_library(   log-lib    log )

target_link_libraries(   cpp-lib   ${log-lib} )