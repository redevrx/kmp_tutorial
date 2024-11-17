package org.redev.rx.app.domain.models

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
@Serializable
data class TaskData @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = "${Uuid.random()}",
    val title: String,
    val subTitle: String,
    val detail: String,
    var isCheck: Boolean = false,
    val imageUrl: String,
)


val listTask = listOf(
    TaskData(
        title = "Desing App",
        subTitle = "Hight Priority",
        detail = "Today 7 PM",
        imageUrl = "https://images.macrumors.com/article-new/2023/05/icloud-photos.jpg",
    ),
    TaskData(
        title = "Call",
        subTitle = "mom",
        detail = "Today 7 PM",
        imageUrl = "https://cdn.prod.website-files.com/634e928d7acf0e5b9297c41b/637b636669bf64693d442fcd_603e16b663e7a57754dc4a1e_Blog%2520Hero.jpeg",
    ),
    TaskData(
        title = "Laundry",
        subTitle = "Tomorrow 9 AM",
        detail = "Today 7 PM",
        imageUrl = "https://www.kitchensbathsunlimited.com/hs-fs/hub/265052/file-1247888328-jpg/Gallery/Album/10880/OmegaOtherRm1.jpg?width=454&name=OmegaOtherRm1.jpg",
    ),
    TaskData(
        title = "Desing App",
        subTitle = "Hight Priority",
        detail = "Today 7 PM",
        imageUrl = "https://images.macrumors.com/article-new/2023/05/icloud-photos.jpg",
    ),
    TaskData(
        title = "Call",
        subTitle = "mom",
        detail = "Today 7 PM",
        imageUrl = "https://cdn.prod.website-files.com/634e928d7acf0e5b9297c41b/637b636669bf64693d442fcd_603e16b663e7a57754dc4a1e_Blog%2520Hero.jpeg",
    ),
    TaskData(
        title = "Laundry",
        subTitle = "Tomorrow 9 AM",
        detail = "Today 7 PM",
        imageUrl = "https://www.kitchensbathsunlimited.com/hs-fs/hub/265052/file-1247888328-jpg/Gallery/Album/10880/OmegaOtherRm1.jpg?width=454&name=OmegaOtherRm1.jpg",
    ),
    TaskData(
        title = "Desing App",
        subTitle = "Hight Priority",
        detail = "Today 7 PM",
        imageUrl = "https://images.macrumors.com/article-new/2023/05/icloud-photos.jpg",
    ),
    TaskData(
        title = "Call",
        subTitle = "mom",
        detail = "Today 7 PM",
        imageUrl = "https://cdn.prod.website-files.com/634e928d7acf0e5b9297c41b/637b636669bf64693d442fcd_603e16b663e7a57754dc4a1e_Blog%2520Hero.jpeg",
    ),
    TaskData(
        title = "Laundry",
        subTitle = "Tomorrow 9 AM",
        detail = "Today 7 PM",
        imageUrl = "https://www.kitchensbathsunlimited.com/hs-fs/hub/265052/file-1247888328-jpg/Gallery/Album/10880/OmegaOtherRm1.jpg?width=454&name=OmegaOtherRm1.jpg",
    ),
    TaskData(
        title = "Desing App",
        subTitle = "Hight Priority",
        detail = "Today 7 PM",
        imageUrl = "https://images.macrumors.com/article-new/2023/05/icloud-photos.jpg",
    ),
    TaskData(
        title = "Call",
        subTitle = "mom",
        detail = "Today 7 PM",
        imageUrl = "https://cdn.prod.website-files.com/634e928d7acf0e5b9297c41b/637b636669bf64693d442fcd_603e16b663e7a57754dc4a1e_Blog%2520Hero.jpeg",
    ),
    TaskData(
        title = "Laundry",
        subTitle = "Tomorrow 9 AM",
        detail = "Today 7 PM",
        imageUrl = "https://www.kitchensbathsunlimited.com/hs-fs/hub/265052/file-1247888328-jpg/Gallery/Album/10880/OmegaOtherRm1.jpg?width=454&name=OmegaOtherRm1.jpg",
    )
)
