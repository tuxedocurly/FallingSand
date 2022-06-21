# FallingSand
<div id="top"></div>

<!-- PROJECT SHIELDS -->


<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">"Falling Sand" Particle Simulator</h3>
  
  ![](https://github.com/tuxedocurly/FallingSand/blob/main/images/SandDisaplyDemo.gif)

  <p align="center">
    This is my implementation of the Falling Sands project, as outlined <a href="http://nifty.stanford.edu/2017/feinberg-falling-sand/assignmentwithoutarrays.html">here</a>!
    <br />
    <br />
    <br />
    ·
    <a href="https://github.com/tuxedocurly/FallingSand/issues">Report Bug</a>
    ·
    <a href="https://github.com/tuxedocurly/FallingSand/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

I wrote this Falling Sand Particle Simulator project to hone my Java skills. I've focused primarily on ensuring that particle interactions are relatively realistic.

Particle Types:
* WOOD - Floats on WATER, buries in SAND, becomes semi-unstable on WATER (WOOD stacks much like logs)
* METAL - Solid surface that particles can't pass through. SAND will slide off this surface if the angle is >= ~25 degrees of slope
* COAL - Hot COALs that set WOOD on fire, turning WOOD particles into COAL particles. Can be turned into CHARCOAL if touched by WATER
* CHARCOAL - What COAL turns into when touched by WATER. Once charcoal is created, it should behave like wood. NOTE: CHARCOAL is assumed to be wet, since it is generally created by being soaked by the WATER particle (users can still create CHARCOAL particles, but they will not be set on fire by COAL since they are assumed to be "soaked" by WATER)
* WATER - implemented to "spray" from wherever a user clicks on the screen. Color varies and shimmers for more realism. Turns COAL into CHARCOAL
* SAND - flows off metal surfaces if the angle is high enough (see METAL above). Stacks symetrically when it falls. Can bury WOOD

There are a number of additional features I plan to implement in the future. See the roadmap below for more details on my plans!

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

Java!

* [JDK11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

Ensure you have JRE and JDK installed (v11 in this case)
* [JDK11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [JRE](https://www.java.com/en/download/manual.jsp)


### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Download to code from this project and unzip it
2. Open a terminal of your choice and CD into the directory containing the downloaded .java files
3. Run the following command to build and launch the application
   ```
   javac SandDisplay.java && java SandDisplay
   ```
4. Have fun! Run some simulations and file bugs if you find any unexpected behavior!

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Refactor particle direction comparisons for better readability (i.e. if (DOWN) instead of if (direction == 0))
- [ ] Refactor particle bounding checks to be contained within class methods for better readability (i.e. if (canMoveLeft()) instead of if (x - 1 > -1))
- [ ] Finish implementing CHARCOAL particles so that they move like wood
- [ ] Add more particle types (i.e. ice that melts over time)
- [ ] Implement some type of "Clear" functionality to clear the screen (ideas: Particle type NUKE that nukes the screen back to EMPTY, boring "Clear" button that immediately sets screen back to EMPTY)
- [ ] Add color variation to all particle types for more realism. Color variation should be bound to particle and not change a given cell on screen redraw
- [ ] "Beautify" the UI using JavaFX or some other library
- [ ] Clean up TODOs left in the java files

See the [open issues](https://github.com/tuxedocurly/FallingSand/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Nothing here for now...

<p align="right">(<a href="#top">back to top</a>)</p>
