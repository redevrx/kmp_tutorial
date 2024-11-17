package org.redev.rx.app.presentation.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import kotlinx.coroutines.delay
import org.redev.rx.app.core.screen.Screen
import org.redev.rx.app.domain.models.TaskData
import org.redev.rx.app.domain.models.listTask
import org.redev.rx.app.ui.theme.*


class HomeScreen : Screen {
    @Composable
    override fun Content(nav: NavHostController) {
        val focusManager = LocalFocusManager.current

        var isShow by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            delay(500)
            isShow = true
        }

        Scaffold(
            modifier = Modifier.clickable(onClick = focusManager::clearFocus),
            backgroundColor = kBackgroundColor,
        ) { innnerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innnerPadding)
                    .padding(horizontal = 16.dp)
                    .windowInsetsPadding(AppBarDefaults.topAppBarWindowInsets)
            )
            {
                item {
                    CustomAppBar()
                }

                item {
                    SearchBox()
                }

                item {
                    TaskCard()
                }

                item {
                    TabMenu()
                }

                item {
                    CurrentTaskBox()
                }

                items(listTask, key = { item -> item.id }) {
                    AnimatedVisibility(
                        visible = isShow,
                        enter = slideInVertically(
                            animationSpec = tween(1500),
                            initialOffsetY = {
                                it / 2
                            }
                        ),
                        exit = slideOutHorizontally(animationSpec = tween(1000))
                    ) {
                        TaskCard(task = it) {
                            //click here
                        }
                    }
                }
            }
        }
    }


    @Composable
    private fun TaskCard(modifier: Modifier = Modifier, task: TaskData, onClick: () -> Unit) {
        Card(
            backgroundColor = kItemColor,
            shape = RoundedCornerShape(20.dp),
            elevation = 4.dp,
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = modifier
                        .padding(start = 8.dp)
                        .padding(top = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        task.title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    )

                    Text(
                        task.title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                        )
                    )
                }

                SubcomposeAsyncImage(
                    task.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = modifier
                        .width(200.dp)
                        .aspectRatio(16 / 6f)
                        .clip(RoundedCornerShape(12.dp)),
                )
            }
        }
    }

    @Composable
    private fun CurrentTaskBox(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                "Current Tasks",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            Icon(
                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                null,
                tint = Color.Gray,
                modifier = modifier.size(40.dp)
            )
        }
    }

    @Composable
    private fun TabMenu(modifier: Modifier = Modifier) {
        val menuItem = listOf("All Card", "Today", "Upcoming", "Completed")
        var indexTab by remember {
            mutableStateOf(0)
        }

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            backgroundColor = kItemColor,
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {

                menuItem.mapIndexed { index, title ->
                    val isIndex = indexTab == index

                    val mColor by animateColorAsState(
                        if (isIndex) kCompleteColor else Color.Transparent,
                        animationSpec = tween(300, 0, FastOutLinearInEasing),
                    )
                    val mColorText by animateColorAsState(
                        if (isIndex) Color.Black else Color.Gray,
                        animationSpec = tween(300, 0, FastOutLinearInEasing),
                    )

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = mColor,
                        modifier = modifier.padding(4.dp)
                            .clickable {
                                indexTab = index
                            },
                        elevation = if (indexTab == 1) 1.dp else 0.dp
                    ) {
                        Text(
                            title,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = mColorText,
                            ),
                            modifier = modifier
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun TaskCard(modifier: Modifier = Modifier) {
        var isShow by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            delay(100)
            isShow = true
        }

        AnimatedVisibility(
            visible = isShow,
            enter = slideInHorizontally(
                animationSpec = tween(1000)
            ),
            exit = slideOutHorizontally(animationSpec = tween(1000))
        ) {
            Surface(
                modifier = modifier
                    .padding(top = 18.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = Color.White.copy(alpha = .23f),
                    ),
                color = kItemColor,
            ) {
                Column(
                    modifier = modifier
                        .padding(12.dp),
                ) {
                    /**
                     * title
                     */
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            "Task Status",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )

                        Card(
                            backgroundColor = kBackgroundColor,
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            Row(
                                modifier = modifier.padding(10.dp),
                            ) {
                                Text(
                                    "Today",
                                    style = TextStyle(
                                        color = Color.Gray,
                                        fontSize = 18.sp,
                                    )
                                )

                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    null,
                                    tint = Color.Gray,
                                    modifier = modifier.size(20.dp),
                                )
                            }
                        }
                    }

                    /**
                     * detail
                     */
                    Text(
                        "You have 9 task due today and 30 other tasks to" +
                                "\ncomplete for the rest of the week.",
                        style = TextStyle(color = Color.Gray),
                        modifier = modifier.padding(vertical = 16.dp)
                    )

                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Box(
                            modifier = modifier
                                .align(Alignment.Center)
                                .size(120.dp)
                                .drawBehind {
                                    drawTaskProgress()
                                })

                        Column(
                            modifier = modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 60.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Pending", style = TextStyle(
                                    color = kPendingColor
                                )
                            )
                            Text(
                                "30%", style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                ),
                                modifier = modifier.padding(bottom = 12.dp)
                            )
                        }


                        Column(
                            modifier = modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = 30.dp, x = 90.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "To-do", style = TextStyle(
                                    color = kTodoColor,
                                )
                            )
                            Text(
                                "70%", style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                ),
                                modifier = modifier.padding(bottom = 12.dp)
                            )
                        }


                        Column(
                            modifier = modifier
                                .offset(x = 72.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                "Complete", style = TextStyle(
                                    color = kCompleteColor,
                                )
                            )
                            Text(
                                "0%", style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                )
                            )

                        }
                    }
                }
            }
        }
    }

    private fun DrawScope.drawTaskProgress() {
        //pending
        drawArc(
            brush = SolidColor(kPendingColor),
            startAngle = 280f,
            sweepAngle = 68f,
            useCenter = false,
            style = Stroke(50f, cap = StrokeCap.Round)
        )

        // to-do
        drawArc(
            brush = SolidColor(kTodoColor),
            startAngle = 368f,
            sweepAngle = 146f,
            useCenter = false,
            style = Stroke(50f, cap = StrokeCap.Round)
        )

        // complete
        drawArc(
            brush = SolidColor(kCompleteColor),
            startAngle = 178f,
            sweepAngle = 80f,
            useCenter = false,
            style = Stroke(50f, cap = StrokeCap.Round)
        )
    }

    @Composable
    private fun SearchBox(modifier: Modifier = Modifier) {
        var textState by remember {
            mutableStateOf(TextFieldValue("Search"))
        }

        Box(
            modifier = modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth(),
                backgroundColor = kItemColor,
                shape = RoundedCornerShape(20.dp),
            ) {
                Row(
                    modifier = modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    Icon(
                        Icons.Outlined.Search, null,
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp),
                    )

                    /**
                     * text feild
                     */
                    BasicTextField(
                        value = textState,
                        onValueChange = { textState = it },
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                        cursorBrush = SolidColor(Color.White)
                    )

                    /**
                     * icon
                     */
                    Card(
                        backgroundColor = kBackgroundColor,
                        shape = CircleShape,
                    ) {
                        Icon(
                            Icons.Rounded.Create,
                            null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun CustomAppBar(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier,
                ) {
                    /**
                     * image profile
                     */
                    val size by animateDpAsState(46.dp)
                    SubcomposeAsyncImage(
                        modifier = modifier
                            .size(size)
                            .clip(CircleShape),
                        model = "https://www.shutterstock.com/image-photo/young-hispanic-man-wearing-casual-260nw-1814647751.jpg",
                        loading = {
                            CircularProgressIndicator()
                        },
                        onError = { error ->
                            println(error.result.throwable)
                        },
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    /**
                     * title and subtitle
                     */
                    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                        Text(
                            "Hello Emily", style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp, fontWeight = FontWeight.Bold,
                            )
                        )
                        Text("You have 9 tasks to do today", style = TextStyle(color = Color.Gray))
                    }
                }

                /**
                 * icon
                 */
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = Color.Gray,
                )
            }
        }
    }

}

