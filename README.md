<div align="center" style="text-align:center;">
  <image src="https://github.com/Coder-ACJHP/Snake-Modern/blob/master/src/com/coder/snake/icons/snake.png">
  <h1>Modern Snake</h1>
</div>
<p>Simple snake game that include core functionality for classic snake but overrided some properties like : user can change background color or can play from control panel and change settings etc.The game builded on Java SE(Swing & AWT) libraries and packaged with Maven.</p>
<p>There is just two external library used in this project : </p>
<ol>
<li>AppleJavaExtentions library (used for adding application icon in dock)</li>
<li>JZoom library (used for playing mp3 files)</li>
</ol>
<div align="center" style="text-align:center;">
  <h1>Modern Snake screen shot</h1>
  <image src="https://github.com/Coder-ACJHP/Snake-Modern/blob/master/src/com/coder/snake/icons/ScreenShot.png">
</div>
<h4>The game designed on mvc pattern</h4>
<ul>
<li>Model : including Snake, Food, Directions and SoundPlayer classes</li>
<li>View : including GamePanel(display), ControlPanel(Control pad) classes</li>
<li>Controller : including MainFrame(Main window *container* ) and InitializeSettings class</li>
</ul>
<h2>How to play ? </h2>
<p>Download the project from here then import it in any IDE (I used Eclipse) then start the /Snake/src/com/coder/snake/start/Snake_Modern.java class and enjoy</p>
<p>You can also control the snake from your keyboard (arrow keys ⬅️ ➡️ ⬆️ ⬇️  )</p>
<p>You can easily export the game as single executable '.jar' with Maven</p>
<h2>How to export the game as single executable jar 'independed platform' ?</h2>
<p>Just add this <code>maven-assembly-plugin</code> into your POM.XML file then run the following code in your IDE or Terminal : </p>
<code>mvn clean compile assembly:single</code><br>
  <p><b>Or download the game as (.app) for MacOS or (.jar) for all OS from this link: </b><a https://github.com/Coder-ACJHP/Modern-Snake/releases">Modern-Snake-1.2.1 releases</a></p>  
<p>That's it 😍🎉 enjoy with your time 👍</p>

<p>For any question 🤔 please 📧 me at : <a href="mailto:hexa.octabin@gmail.com">hexa.octabin@gmail.com</a></p>
<h3>I hope this help to juniors learn basics of building game via Java, thank you for reading 😊</h3>
