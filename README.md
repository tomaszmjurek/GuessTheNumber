<h1 align="center" style="display: block; font-size: 2.5em; font-weight: bold; margin-block-start: 1em; margin-block-end: 1em;">
<a name="logo" href="https://www.aregtech.com"><img align="center" src="https://raw.githubusercontent.com/tomaszmjurek/GuessTheNumber/master/app/src/main/ic_launcher-playstore.png" alt="AREG SDK Home" style="width:20%;height:20%"/></a>
  <br /><br /><strong>Guess The Number</strong>
</h1>

[![Latest release](https://img.shields.io/github/v/release/aregtech/areg-sdk?label=Latest%20release&style=social)](https://github.com/tomaszmjurek/GuessTheNumber/releases/tag/v2.0)

---

<!-- markdownlint-disable -->
## Project Info[![](https://raw.githubusercontent.com/aregtech/areg-sdk/master/docs/img/pin.svg)](#project-status)
<table class="no-border">
  <tr>
    <td><img src="https://img.shields.io/badge/Solution-Kotlin-blue" alt="Kotlin"/></td>
    <td><img src="https://img.shields.io/badge/System-Android-blue" alt="Android"/></td>
    <td><img src="https://img.shields.io/badge/Database-SQLite-blue" alt="SQLite"/></td>
  </tr>
</table>

---

## Introduction[![](https://raw.githubusercontent.com/aregtech/areg-sdk/master/docs/img/pin.svg)](#introduction)

**GuessTheNumber** is a simple Android game. User has to guess the number with given tips (try less/more) and get points. Includes splash screen with logo, registration/login and view showing the scores of the device users.

---

## Table of contents[![](https://raw.githubusercontent.com/aregtech/areg-sdk/master/docs/img/pin.svg)](#table-of-contents)
- [Project Status](#project-status)
- [Introduction](#introduction)
- [Table of contents](#table-of-contents)
- [Motivation](#motivation)


---

## Motivation[![](https://raw.githubusercontent.com/aregtech/areg-sdk/master/docs/img/pin.svg)](#motivation)

...

<div align="right">[ <a href="#table-of-contents">↑ to top ↑</a> ]</div>

---

## Software build[![](https://raw.githubusercontent.com/aregtech/areg-sdk/master/docs/img/pin.svg)](#software-build)

> 💡 Check the [Wiki page](https://github.com/aregtech/areg-sdk/wiki) of _AREG SDK_ for mode details. We change the content and add more details.

The source codes of AREG framework and examples support following platform, CPU and compilers:
<table>
  <tr>
    <td nowrap><strong>OS</strong></td>
    <td><i>Linux</i> (list of <a href="./docs/POSIX.md#posix-api" alt="POSX API">POSIX API</a>), <i>Windows 8</i> and higher.</td>
  </tr>
  <tr>
    <td nowrap><strong>CPU</strong></td>
    <td><i>x86</i>, <i>x86_64</i>, <i>arm</i> and <i>aarch64</i>.</td>
  </tr>
  <tr>
    <td nowrap><strong>Compilers</strong></td>
    <td><i>GCC</i>, <i>g++</i>, <i>clang</i>, <i>MSVC</i> and <i>cygwin CC</i>.</td>
  </tr>
</table>

The tools to use to compile sources:
| Solution | Platforms | API | Quick actions to compile |
| --- | --- | --- | --- |
| `CMakeLists.txt` | **Linux, Windows** | _POSIX_, _Win32_ | Make one of these actions:<br /> &nbsp;&nbsp; - Call `cmake` in _command line_.<br /> &nbsp;&nbsp; - Configure and build in _Visual Studio Code_;<br /> &nbsp;&nbsp; - Configure and build in _Microsoft Visual Studio_. |
| `Makefile` | **Linux**| _POSIX_ | Call `make` in _command line_. |
| `areg-sdk.sln` | **Windows** | _Win32_ | Open and build in _Microsoft Visual Studio_. |
| `.project` | **Linux, Windows** | _POSIX_ | Import and build projects in _Eclipse_. |

> 💡 The other POSIX-compliant OS and compilers are not tested yet.<br />
> 💡 Make user specific changes (like switch compiler or output folder) only in appropriate `user` files:<br />
> - For `cmake`, make changes in [conf/cmake/user.cmake](./conf/cmake/user.cmake) file.<br />
> - For `make`, make changes in [conf/make/user.mk](./conf/make/user.mk) file.
> - For `MSBuild`, make changes in [conf/msvc/user.props](./conf/msvc/user.props) file.

After compilation, normally binaries are located in `<areg-sdk>/product/build/<compiler-platform-path>/bin` folder. Details on how to change compiler, load and compile sources for various targets are described in [HOWTO](./docs/HOWTO.md) document. The next are quick overviews.

#### Build with `cmake`

To build with [cmake](https://cmake.org/), open _Terminal_ in your `projects` folder and take the steps:
```bash
# Step 1: Get sources from GitHub including submodules
$ git clone --recurse-submodules https://github.com/aregtech/areg-sdk.git
$ cd areg-sdk

# Step 2: Initialize cache and build configuration in folder './build' folder.
#         The switch 'BUILD_EXAMPLES' enable or disables examples. By default, it is enabled.
#         Example: cmake -B ./build -DBUILD_EXAMPLES=OFF
$ cmake -B ./build

# Step 3: Compile sources by calling: cmake --build ./build [-j [concurrent jobs]]
$ cmake --build ./build -j8
 ```
> 💡 The Unit Tests of AREG SDK need [googletest](https://github.com/google/googletest), which is set as a submodule and compiled only with `cmake` tool. Use the mentioned `git` command to clone the submodule. If already have AREG SDK sources and only need to clone `googletest` submodule, call:<br />
```bash
$ cd areg-sdk
$ git submodule update --init
```

#### Build with `make`

To build with [make](https://www.gnu.org/software/make/), open _Terminal_ in your `projects` folder and take the steps:
```bash
# Step 1: Get sources from GitHub
$ git clone --recurse-submodules https://github.com/aregtech/areg-sdk.git
$ cd areg-sdk

# Step 2: Compile sources by calling: make [all] [framework] [examples]
$ make
```

#### Build with IDE

Open _Terminal_ or preferred IDE to clone source codes like this:
```bash
$ git clone --recurse-submodules https://github.com/aregtech/areg-sdk.git
$ cd areg-sdk
```
Depending on IDE or preferences make one of following:

1. Open `areg-sdk.sln` file in [MSVS](https://visualstudio.microsoft.com/) (2019 or higher) and compile solution.
2. Open `areg-sdk` folder in [MSVS](https://visualstudio.microsoft.com/) (2019 or higher), select `CMakeLists.txt` in `areg-sdk` root, configure and build.
3. Open `areg-sdk` folder in [VS Code](https://code.visualstudio.com/), select `CMakeLists.txt` in `areg-sdk` root, configure and build.
4. Open [Eclipse](https://www.eclipse.org/ide/), import all projects from `areg-sdk` root, select projects and build.

> 💡 Compilation with _Eclipse_ under **Windows** might require to change the Toolchain.

<div align="right">[ <a href="#table-of-contents">↑ to top ↑</a> ]</div>

---

## Examples[![](https://raw.githubusercontent.com/aregtech/areg-sdk/master/docs/img/pin.svg)](#examples)

There are various [examples](./examples/) to demonstrate features of the AREG SDK. The examples are listed in the [examples/README.md](./examples/README.md) document.

<div align="right">[ <a href="#table-of-contents">↑ to top ↑</a> ]</div>

---

## Licensing[![](./docs/img/pin.svg)](#licensing)
 
GuessTheNumber is under free open source [_Apache License Version 2.0_](./LICENSE.txt). 

<div align="right">[ <a href="#table-of-contents">↑ to top ↑</a> ]</div>

---

**Follow me** at<br />
[![Follow us on LinkedIn](https://img.shields.io/badge/LinkedIn-Aregtech-blue?style=flat&logo=linkedin&logoColor=b0c0c0&labelColor=363D44)](https://www.linkedin.com/company/aregtech)

<!-- markdownlint-enable -->
