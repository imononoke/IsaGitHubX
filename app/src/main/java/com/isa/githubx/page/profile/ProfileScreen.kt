package com.isa.githubx.page.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.isa.githubx.R
import com.isa.githubx.model.RepoEntity
import com.isa.githubx.model.UserInfo
import com.isa.githubx.uikit.Icons
import com.isa.githubx.uikit.theme.Dimens
import com.isa.githubx.uikit.widgets.PrimaryButton
import com.isa.githubx.uikit.widgets.Title

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
    toLogout: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // https://github.com/hmkcode/Android.git
        // avatar
        Title(
            text = "Profile"
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(Dimens.ScreenEdgePadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_navigation_profile), // todo
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "hmkcode",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "https://github.com/hmkcode/Android.git",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(Dimens.ScreenEdgePadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "repos",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "4",
                    style = MaterialTheme.typography.h6
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "followers",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "4",
                    style = MaterialTheme.typography.h6
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "follows",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "6",
                    style = MaterialTheme.typography.h6
                )
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
        PrimaryButton(
            text = stringResource(R.string.log_out),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                toLogout()
            }
        )
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
