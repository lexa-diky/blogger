# AKore Blogger

Simplistic logger facade. Because you don't need anything more.

## Usage

### Setup

#### Write your logger delegate

```kotlin  
internal class MyLogger : LoggerDelegate {  
  
 override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) { println("$tag: $message") }}  
```  

Or use one of pre-defined loggers:
- (android) logical

#### Attach desired logger

```kotlin  
BLogger.configure { 
    source pipeTo MyLogger()
}  
```

### Log

```kotlin
// set tag and call method with name matching level
BLogger.tag("MyTag")
	.info("MY_MESSAGE")

// or let blogger make tag for you
BLogger.tagged()
	.info("MY_MESSAGE")

// or if you need more control
BLogger.log(  
	level = LoggerLevel.DEBUG,  
    tag = "MY_TAG",  
    message = "MY_MESSAGE",  
    throwable = Exception("Oooops!")
)

// or perform logged assertion
BLogger.tag("MyTag")
	.assert("MY_MESSAGE", 2 * 2 == 4) // will throw IllegalStateException if not true
```
