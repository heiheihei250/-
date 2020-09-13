using UnityEngine;
using System.Collections;
//主要是触发检测
public class shell : MonoBehaviour {
	public GameObject shellExplosionPrefab;
	public AudioClip shellExplosionAudio;
	// Use this for initialization
	void Start () {
		
	}

	// Update is called once per frame
	void Update()
	{

	}
	public void OnTriggerEnter(Collider collider)//确定是什么碰撞器 这里是胶囊
	{
		AudioSource.PlayClipAtPoint(shellExplosionAudio, transform.position);
		GameObject.Instantiate(shellExplosionPrefab, transform.position, transform.rotation);
		GameObject.Destroy(this.gameObject);//销毁自己

		if (collider.tag == "Tank")
		{
			collider.SendMessage("TakeDamage");
		}
	}
}
