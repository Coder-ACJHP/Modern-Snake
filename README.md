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

<h4>You can easily export the game as single executable '.jar' with Maven</h4>
<h4>How to export the game as single executable jar 'independed platform' ?</h4>
<p>Just add this <code>maven-assembly-plugin</code> into your POM.XML file then run the following code in your IDE or Terminal : </p>
<pre>mvn clean compile assembly:single</pre>
<p>That's it 😍🎉 enjoy with your time 👍</p>

<h2>For any question 🤔 please 📧 me at : <a href="mailto:hexa.octabin@gmail.com">hexa.octabin@gmail.com</a></h2>
<h3>I hope this help to juniors learn basics of building game via Java, thank you for reading 😊</h3>
