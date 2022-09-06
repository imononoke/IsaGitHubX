package com.isa.githubx.page.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.material.placeholder
import com.isa.githubx.model.UserInfo
import com.isa.githubx.uikit.Icons
import com.isa.githubx.uikit.theme.Dimens
import com.isa.githubx.uikit.theme.MaterialColors
import com.isa.githubx.uikit.widgets.CommonButton
import com.isa.githubx.R

@Composable
fun ProfileScreen(
    toLogout: () -> Unit,
) {
    ProfileContent(
        toLogout = toLogout
    )
}

@Composable
fun ProfileContent(
//    userInfo: UserInfo,
    toLogout: () -> Unit,
) {
    val userInfo = UserInfo(
        id="2", login="defunkt",
        avatar_url="https://avatars.githubusercontent.com/u/2?v=4",
        name=null,
        public_repos = 8,
        public_gists=null,
        followers = 3,
        following=6,
        email=null, blog=null, company=null, location=null,
        url= "https://api.github.com/users/defunkt",
        bio=null, node_id = "MDQ6VXNlcjI=",
        total_private_repos = 3,
        html_url="https://github.com/defunkt"
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(Dimens.ScreenEdgePadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // avatar
            Image(
                painter = rememberImagePainter(
                    data = userInfo.avatar_url,
                    builder = {
                        placeholder(R.drawable.ic_navigation_profile)
                    }
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp)
                    .placeholder(
                        visible = false,
                        color = MaterialColors.onBackground
                    )
                    .clip(shape = MaterialTheme.shapes.medium),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(20.dp))

            // name
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                userInfo.login?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                userInfo.url?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // list

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(Dimens.ScreenEdgePadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // item name
                Text(
                    text = "repos",
                    style = MaterialTheme.typography.h6
                )

                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    if (userInfo.public_repos != null) Text(
                        text = userInfo.public_repos.toString(),
                        style = MaterialTheme.typography.h6
                    )
                    else Text(
                        text = "0",
                        style = MaterialTheme.typography.h6
                    )
                    Image(
                        painter = painterResource(Icons.ChevronRight),
                        contentDescription = null
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "followers",
                    style = MaterialTheme.typography.h6
                )

                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    if (userInfo.followers != null) Text(
                        text = userInfo.followers.toString(),
                        style = MaterialTheme.typography.h6
                    )
                    else Text(
                        text = "0",
                        style = MaterialTheme.typography.h6
                    )
                    Image(
                        painter = painterResource(Icons.ChevronRight),
                        contentDescription = null
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "following",
                    style = MaterialTheme.typography.h6
                )

                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    if (userInfo.following != null) Text(
                        text = userInfo.following.toString(),
                        style = MaterialTheme.typography.h6
                    )
                    else Text(
                        text = "0",
                        style = MaterialTheme.typography.h6
                    )
                    Image(
                        painter = painterResource(Icons.ChevronRight),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "settings",
                    style = MaterialTheme.typography.h6
                )
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(Icons.Star), // >
                    contentDescription = null,
                )
            }
        }

        // log out btn
        CommonButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = toLogout
        ) {
            Text((stringResource(R.string.log_out)))
        }
    }
}

@Composable
private fun Item(
    userInfo: UserInfo
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Image

        // text: name

        // text: nums

        // icon
    }
}
