using UnityEngine;
using System.Collections;

public class TankHealth : MonoBehaviour
{

	// Use this for initialization
	public int hp = 100;
	public GameObject tankExplosion;
	public AudioClip tankExplosionAudio;
	void Start()
	{

	}

	// Update is called once per frame
	void Update()
	{

	}
	void TakeDamage()
	{
		if (hp <= 0) return;
		hp -= Random.Range(10, 20);
		if (hp <= 0)//受到伤害之后 判断是否死亡
		{
			AudioSource.PlayClipAtPoint(tankExplosionAudio, transform.position);//PlayClipAtPoint播放音效可以设置位置；
			GameObject.Instantiate(tankExplosion, transform.position + Vector3.up, transform.rotation);
			GameObject.Destroy(this.gameObject);
		}
	}
}
