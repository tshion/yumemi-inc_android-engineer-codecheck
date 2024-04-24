#!/bin/bash

if gh release view $1 > /dev/null; then
  echo "$1 is already released!"
  exit 1
fi
